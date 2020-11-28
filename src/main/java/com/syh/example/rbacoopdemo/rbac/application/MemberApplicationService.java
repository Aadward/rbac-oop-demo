package com.syh.example.rbacoopdemo.rbac.application;

import com.syh.example.rbacoopdemo.rbac.application.dto.AuthDto;
import com.syh.example.rbacoopdemo.rbac.application.identity.*;
import com.syh.example.rbacoopdemo.rbac.domain.member.*;
import com.syh.example.rbacoopdemo.rbac.domain.role.Auth;
import com.syh.example.rbacoopdemo.rbac.domain.role.Role;
import com.syh.example.rbacoopdemo.rbac.domain.role.RoleRepository;
import com.syh.example.rbacoopdemo.rbac.exception.RoleCanNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberApplicationService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final DeptRepository deptRepository;

    private final UserService userService;
    private final GroupService groupService;
    private final DeptService deptService;

    public void grantRoleToUser(String companyId, Long userId, Long roleId) {
        UserInfo userInfo = userService.findUser(companyId, userId);

        Role role = roleRepository.findByIdAndCompany_Id(roleId, companyId)
                .orElseThrow(() -> new RoleCanNotFoundException("Can not find role:" + roleId));

        User user = userRepository.findByCompany_IdAndUserId(userInfo.getCompanyId(), userInfo.getUserId())
                .orElseGet(() -> userRepository.save(
                        User.of(userInfo.getCompanyId(),
                                userInfo.getUserId())));
        user.grantRole(role.getId());
    }

    public void grantRoleToGroup(String companyId, Long groupId, Long roleId) {
        GroupInfo groupInfo = groupService.findGroup(companyId, groupId);

        Role role = roleRepository.findByIdAndCompany_Id(roleId, companyId)
                .orElseThrow(() -> new RoleCanNotFoundException("Can not find role:" + roleId));

        Group group = groupRepository.findByCompany_IdAndGroupId(groupInfo.getCompanyId(), groupInfo.getGroupId())
                .orElseGet(() -> groupRepository.save(
                        Group.of(
                                groupInfo.getCompanyId(),
                                groupInfo.getGroupId())));

        group.grantRole(role.getId());
    }

    public void grantRoleToDept(String companyId, Long deptId, Long roleId) {
        DeptInfo deptInfo = deptService.findDept(companyId, deptId);

        Role role = roleRepository.findByIdAndCompany_Id(roleId, companyId)
                .orElseThrow(() -> new RoleCanNotFoundException("Can not find role:" + roleId));

        Dept dept = deptRepository.findByCompany_IdAndDeptId(deptInfo.getCompanyId(), deptInfo.getDeptId())
                .orElseGet(() -> deptRepository.save(
                        Dept.of(
                                deptInfo.getCompanyId(),
                                deptInfo.getDeptId())));

        dept.grantRole(role.getId());
    }

    public boolean isUserHasAuth(String companyId, Long userId, AuthDto authDto) {
        Auth auth = authDto.toAuth();

        // check user
        Optional<User> user = userRepository.findByCompany_IdAndUserId(companyId, userId);
        if (user.isPresent()) {
            for (Role role : roleRepository.findAllById(user.get().getRoleIds())) {
                if (role.hasAuth(auth.getResource(), auth.getAuthType())) {
                    return true;
                }
            }
        }

        // check group
        List<GroupInfo> userGroups = groupService.findGroupsByUser(companyId, userId);


        for (Group group : groupRepository.findByCompany_IdAndGroupIdIn(
                companyId,
                userGroups.stream()
                        .map(GroupInfo::getGroupId)
                        .collect(Collectors.toList()))) {

            for (Role role : roleRepository.findAllById(group.getRoleIds())) {
                if (role.hasAuth(auth.getResource(), auth.getAuthType())) {
                    return true;
                }
            }

        }

        // check dept
        List<DeptInfo> userDepts = deptService.findDeptsByUser(companyId, userId);
        for (Dept dept : deptRepository.findByCompany_IdAndDeptIdIn(
                companyId,
                userDepts.stream()
                        .map(DeptInfo::getDeptId)
                        .collect(Collectors.toList()))) {

            for (Role role : roleRepository.findAllById(dept.getRoleIds())) {
                if (role.hasAuth(auth.getResource(), auth.getAuthType())) {
                    return true;
                }
            }
        }
        return false;
    }

}
