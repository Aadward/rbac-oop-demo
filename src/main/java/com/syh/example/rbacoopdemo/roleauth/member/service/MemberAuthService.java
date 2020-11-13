package com.syh.example.rbacoopdemo.roleauth.member.service;

import org.springframework.stereotype.Component;

import com.syh.example.rbacoopdemo.roleauth.auth.Auth;
import com.syh.example.rbacoopdemo.roleauth.auth.AuthType;
import com.syh.example.rbacoopdemo.roleauth.member.Member;
import com.syh.example.rbacoopdemo.roleauth.member.MemberKey;
import com.syh.example.rbacoopdemo.roleauth.member.external.MemberBelongService;
import com.syh.example.rbacoopdemo.roleauth.member.repository.MemberRepository;
import com.syh.example.rbacoopdemo.roleauth.resource.Menu;
import com.syh.example.rbacoopdemo.roleauth.resource.Resource;
import com.syh.example.rbacoopdemo.roleauth.resource.ResourceType;
import com.syh.example.rbacoopdemo.roleauth.role.Role;
import com.syh.example.rbacoopdemo.roleauth.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;

/**
 * @author shen.yuhang
 * created on 2020/11/12
 **/

@Component
@RequiredArgsConstructor
public class MemberAuthService {

	private final RoleRepository roleRepository;
	private final MemberBelongService memberBelongService;
	private final MemberRepository memberRepository;

	public boolean hasFullAuthForMenu(MemberKey memberKey, Menu menu) {
		return hasAuth(memberKey, new Resource(ResourceType.MENU, menu.getResourceKey()), AuthType.FULL);
	}

	public boolean hasAuth(MemberKey memberKey, Resource resource, AuthType authType) {
		Auth targetAuth = new Auth(authType, resource);

		// check for its group and dept
		for (MemberKey otherMemberKey : memberBelongService.memberBelongTo(memberKey)) {
			Member member = memberRepository.selectByMemberKey(otherMemberKey);
			if (memberHasAuth(member, targetAuth)) {
				return true;
			}
		}

		// check auth for itself
		Member member = memberRepository.selectByMemberKey(memberKey);
		if (member == null) {
			// maybe there isn't this member (don't care here, it is job for user/group/dept domain)
			// maybe the member is not registered any role.
			return false;
		}

		if (memberHasAuth(member, targetAuth)) {
			return true;
		}

		return false;
	}

	private boolean memberHasAuth(Member member, Auth targetAuth) {
		for (Role role : roleRepository.findByIdList(member.getRoleIds())) {
			if (role.hasAuth(targetAuth)) {
				return true;
			}
		}
		return false;
	}
}
