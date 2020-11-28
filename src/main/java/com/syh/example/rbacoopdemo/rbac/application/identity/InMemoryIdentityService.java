package com.syh.example.rbacoopdemo.rbac.application.identity;

import com.google.common.collect.Lists;
import com.syh.example.rbacoopdemo.rbac.exception.DeptNotFoundException;
import com.syh.example.rbacoopdemo.rbac.exception.GroupNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//TODO:
public class InMemoryIdentityService implements DeptService, GroupService, UserService {


    @Override
    public DeptInfo findDept(String companyId, Long deptId) throws DeptNotFoundException {
        return new DeptInfo(companyId, deptId);
    }

    @Override
    public List<DeptInfo> findSubDepts(String companyId, Long deptId) throws DeptNotFoundException {
        return Lists.newArrayList();
    }

    @Override
    public List<UserInfo> findUsersInDept(String companyId, Long deptId) throws DeptNotFoundException {
        return Lists.newArrayList();
    }

    @Override
    public List<DeptInfo> findDeptsByUser(String companyId, Long userId) {
        return Lists.newArrayList();
    }

    @Override
    public GroupInfo findGroup(String companyId, Long groupId) throws GroupNotFoundException {
        return new GroupInfo(companyId, groupId);
    }

    @Override
    public List<UserInfo> findUsersInGroup(String companyId, Long groupId) throws GroupNotFoundException {
        return Lists.newArrayList();
    }

    @Override
    public List<GroupInfo> findGroupsByUser(String companyId, Long userId) {
        return Lists.newArrayList();
    }

    @Override
    public UserInfo findUser(String companyId, Long userId) {
        return new UserInfo(userId, companyId);
    }
}
