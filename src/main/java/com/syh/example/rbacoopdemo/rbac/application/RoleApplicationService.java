package com.syh.example.rbacoopdemo.rbac.application;

import com.google.common.collect.Lists;
import com.syh.example.rbacoopdemo.rbac.application.command.CreateRoleCommand;
import com.syh.example.rbacoopdemo.rbac.application.dto.AuthDto;
import com.syh.example.rbacoopdemo.rbac.domain.role.Role;
import com.syh.example.rbacoopdemo.rbac.domain.role.RoleRepository;
import com.syh.example.rbacoopdemo.rbac.exception.RoleCanNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleApplicationService {

    private final RoleRepository roleRepository;

    public Role createRole(CreateRoleCommand command) {
        Role role = Role.newRole(command.getCompanyId(), command.getRoleName());
        for (AuthDto authDto : command.getAuthList()) {
            role.giveAuth(authDto.toAuth());
        }

        roleRepository.save(role);
        return role;
    }

    public void grantAuth(String companyId, Long roleId, AuthDto authDto) {
        Role role = roleRepository.findByIdAndCompany_Id(roleId, companyId)
                .orElseThrow(() -> new RoleCanNotFoundException("Can not find role: " + roleId));

        role.giveAuth(authDto.toAuth());
    }

    public void removeAuth(String companyId, Long roleId, AuthDto authDto) {
        Role role = roleRepository.findByIdAndCompany_Id(roleId, companyId)
                .orElseThrow(() -> new RoleCanNotFoundException("Can not find role: " + roleId));

        role.removeAuth(authDto.toAuth());
    }

    public void deleteRole(String companyId, Long roleId) {
        Role role = roleRepository.findByIdAndCompany_Id(roleId, companyId)
                .orElseThrow(() -> new RoleCanNotFoundException("Can not find role: " + roleId));

        roleRepository.delete(role);
    }

    public List<Role> allRolesOfCompany(String companyId) {
        return Lists.newArrayList(roleRepository.findByCompany_Id(companyId));
    }

    public Role find(String companyId, Long roleId) {
        return roleRepository.findByIdAndCompany_Id(roleId, companyId)
                .orElseThrow(() -> new RoleCanNotFoundException("Can not find role: " + roleId));
    }
}
