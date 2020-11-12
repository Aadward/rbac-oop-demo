package com.syh.example.rbacoopdemo.roleauth.member.repository.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syh.example.rbacoopdemo.roleauth.member.repository.po.MemberRolePo;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/12
 **/

@Component
public class MemberRoleDao extends ServiceImpl<MemberRoleMapper, MemberRolePo> {

	public void deleteByMemberId(Long memberId) {
		QueryWrapper<MemberRolePo> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("member_id", memberId);
		remove(queryWrapper);
	}

	public List<MemberRolePo> selectByMemberId(Long memberId) {
		return query()
			.eq("member_id", memberId)
			.list();
	}
}
