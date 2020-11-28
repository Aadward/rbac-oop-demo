package com.syh.example.rbacoopdemo.rbac.application.identity;

import com.syh.example.rbacoopdemo.rbac.exception.DeptNotFoundException;

import java.util.List;

public interface DeptService {

    DeptInfo findDept(String companyId, Long deptId) throws DeptNotFoundException;

    List<DeptInfo> findSubDepts(String companyId, Long deptId) throws DeptNotFoundException;

    List<UserInfo> findUsersInDept(String companyId, Long deptId) throws DeptNotFoundException;

    List<DeptInfo> findDeptsByUser(String companyId, Long userId);
}
