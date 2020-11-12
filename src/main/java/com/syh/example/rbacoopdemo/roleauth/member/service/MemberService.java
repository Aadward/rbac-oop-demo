package com.syh.example.rbacoopdemo.roleauth.member.service;

import org.springframework.stereotype.Component;

import com.syh.example.rbacoopdemo.roleauth.auth.Auth;
import com.syh.example.rbacoopdemo.roleauth.auth.AuthType;
import com.syh.example.rbacoopdemo.roleauth.member.Member;
import com.syh.example.rbacoopdemo.roleauth.resource.Menu;
import com.syh.example.rbacoopdemo.roleauth.resource.Resource;
import com.syh.example.rbacoopdemo.roleauth.resource.ResourceType;
import com.syh.example.rbacoopdemo.roleauth.role.Role;
import com.syh.example.rbacoopdemo.roleauth.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/12
 **/

@Component
@RequiredArgsConstructor
public class MemberService {

	private final RoleRepository roleRepository;

	public boolean hasFullAuthForMenu(Member member, Menu menu) {
		return hasAuth(member, new Resource(ResourceType.MENU, menu.getResourceKey()), AuthType.FULL);
	}

	public boolean hasAuth(Member member, Resource resource, AuthType authType) {
		Auth targetAuth = new Auth(authType, resource);

		for (Role role : roleRepository.findByIdList(member.getRoleIds())) {
			if (role.hasAuth(targetAuth)) {
				return true;
			}
		}
		return false;
	}
}
