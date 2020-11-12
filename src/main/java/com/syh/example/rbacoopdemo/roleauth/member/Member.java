package com.syh.example.rbacoopdemo.roleauth.member;

import java.util.List;

import org.springframework.util.Assert;

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

	private String companyId;

	private MemberType memberType;

	private Long orgMemberId;

	public Member(
		String companyId,
		MemberType memberType,
		Long orgMemberId) {
		this.companyId = companyId;
		this.memberType = memberType;
		this.orgMemberId = orgMemberId;
		this.roleIds = Lists.newArrayList();
	}

	@Getter
	private List<Long> roleIds;

	public void grantRole(Role role) {
		Assert.isTrue(this.companyId.equals(role.getCompanyId()), "Member companyId should same as role companyId");
		this.roleIds.add(role.getId());
	}
}
