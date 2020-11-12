package com.syh.example.rbacoopdemo.roleauth.member;

import java.util.List;

import com.google.common.collect.Lists;
import com.syh.example.rbacoopdemo.roleauth.role.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/12
 **/

@Getter
@AllArgsConstructor
public class Member {

	private Long id;

	private MemberType memberType;

	private Long orgMemberId;

	public Member(MemberType memberType,
		Long orgMemberId) {
		this.memberType = memberType;
		this.orgMemberId = orgMemberId;
		this.roleIds = Lists.newArrayList();
	}

	@Getter
	private List<Long> roleIds;

	public void grantRole(Role role) {
		this.roleIds.add(role.getId());
	}
}
