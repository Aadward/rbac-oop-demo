package com.syh.example.rbacoopdemo.roleauth.role.repository.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syh.example.rbacoopdemo.roleauth.role.repository.po.RoleAuthPo;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/12
 **/

@Component
public class RoleAuthDao extends ServiceImpl<RoleAuthMapper, RoleAuthPo> {

	public List<RoleAuthPo> selectByRoleId(Long roleId) {
		return query()
			.eq("role_id", roleId)
			.list();
	}

	public void deleteByRoleId(Long roleId) {
		QueryWrapper<RoleAuthPo> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("role_id", roleId);
		remove(queryWrapper);
	}
}
