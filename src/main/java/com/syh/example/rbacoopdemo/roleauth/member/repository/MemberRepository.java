package com.syh.example.rbacoopdemo.roleauth.member.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.syh.example.rbacoopdemo.roleauth.member.Member;
import com.syh.example.rbacoopdemo.roleauth.member.MemberType;
import com.syh.example.rbacoopdemo.roleauth.member.repository.mapper.MemberDao;
import com.syh.example.rbacoopdemo.roleauth.member.repository.mapper.MemberRoleDao;
import com.syh.example.rbacoopdemo.roleauth.member.repository.po.MemberRolePo;
import com.syh.example.rbacoopdemo.roleauth.member.repository.po.MemberPo;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/12
 **/
@Repository
@RequiredArgsConstructor
public class MemberRepository {

	private final MemberDao memberDao;
	private final MemberRoleDao memberRoleDao;

	public void save(Member member) {
		MemberPo memberPo = new MemberPo(member.getId(),
			member.getMemberType().name(),
			member.getOrgMemberId());

		memberDao.save(memberPo);

		List<MemberRolePo> objectRolePos = member.getRoleIds()
			.stream()
			.map(roleId -> new MemberRolePo(null, memberPo.getId(), roleId))
			.collect(Collectors.toList());
		memberRoleDao.deleteByMemberId(memberPo.getId());
		memberRoleDao.saveBatch(objectRolePos);

	}

	public Member selectByTypeAndId(MemberType type, Long id) {
		MemberPo memberPo = memberDao.selectByTypeAndOrgMemberId(type, id);
		List<Long> roleIds = memberRoleDao.selectByMemberId(memberPo.getId())
			.stream()
			.map(MemberRolePo::getRoleId)
			.collect(Collectors.toList());

		return new Member(memberPo.getId(), type, id, roleIds);

	}
}
