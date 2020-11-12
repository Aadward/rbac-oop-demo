package com.syh.example.rbacoopdemo.roleauth.role.repository;

import java.util.List;

import com.syh.example.rbacoopdemo.roleauth.role.Role;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/12
 **/

public interface RoleRepository {

	List<Role> findByIdList(List<Long> roleIds);

	void save(Role role);
}
