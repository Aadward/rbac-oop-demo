package com.syh.example.rbacoopdemo.roleauth.role;

import java.util.List;

import com.syh.example.rbacoopdemo.roleauth.role.assign.RoleAssign;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/11
 **/
public interface RoleService {

	Role createRole(String tenantKey, String roleKey, List<Long> authIds);

	List<Role> getRolesByTenant(String tenantKey);

	void assign(String tenantKey, String roleKey, RoleAssign roleAssign);

	boolean hasAuth(RoleAssign roleAssign, String resourceKey);
}
