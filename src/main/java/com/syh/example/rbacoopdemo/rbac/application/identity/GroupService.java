package com.syh.example.rbacoopdemo.rbac.application.identity;

import com.syh.example.rbacoopdemo.rbac.exception.GroupNotFoundException;

import java.util.List;

public interface GroupService {

    GroupInfo findGroup(String companyId, Long groupId) throws GroupNotFoundException;

    List<UserInfo> findUsersInGroup(String companyId, Long groupId) throws GroupNotFoundException;

    List<GroupInfo> findGroupsByUser(String companyId, Long userId);
}
