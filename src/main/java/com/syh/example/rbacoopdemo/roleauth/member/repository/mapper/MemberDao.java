package com.syh.example.rbacoopdemo.roleauth.member.repository.mapper;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syh.example.rbacoopdemo.roleauth.member.MemberType;
import com.syh.example.rbacoopdemo.roleauth.member.repository.po.MemberPo;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/12
 **/

@Component
public class MemberDao extends ServiceImpl<MemberMapper, MemberPo> {

	public MemberPo selectByCompanyIdAndTypeAndOrgMemberId(String companyId,
		MemberType memberType,
		Long orgMemberId) {

		return query()
			.eq("company_id", companyId)
			.eq("member_type", memberType.name())
			.eq("org_member_id", orgMemberId)
			.one();
	}
}
