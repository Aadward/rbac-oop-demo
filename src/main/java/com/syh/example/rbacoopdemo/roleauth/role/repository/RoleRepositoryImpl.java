package com.syh.example.rbacoopdemo.roleauth.role.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.syh.example.rbacoopdemo.roleauth.auth.Auth;
import com.syh.example.rbacoopdemo.roleauth.role.Role;
import com.syh.example.rbacoopdemo.roleauth.role.repository.mapper.RoleAuthDao;
import com.syh.example.rbacoopdemo.roleauth.role.repository.mapper.RoleDao;
import com.syh.example.rbacoopdemo.roleauth.role.repository.po.RoleAuthPo;
import com.syh.example.rbacoopdemo.roleauth.role.repository.po.RolePo;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/12
 **/

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {

	private final RoleDao roleDao;
	private final RoleAuthDao roleAuthDao;

	@Override
	public List<Role> findByIdList(List<Long> roleIds) {
		if (roleIds.isEmpty()) {
			return Lists.newArrayList();
		}

		List<RolePo> rolePos = roleDao.getBaseMapper().selectBatchIds(roleIds);

		return rolePos.stream()
			.map(rolePo -> {
				List<Auth> authList = roleAuthDao.selectByRoleId(rolePo.getId())
					.stream()
					.map(RoleAuthPo::toAuth)
					.collect(Collectors.toList());
				return new Role(rolePo.getId(), rolePo.getCompanyId(), rolePo.getName(), authList);
			})
			.collect(Collectors.toList());
	}

	@Override
	public void save(Role role) {
		RolePo rolePo = new RolePo(role.getId(), role.getCompanyId(), role.getName());
		roleDao.saveOrUpdate(rolePo);

		List<RoleAuthPo> roleAuthPos = role.getAuthList()
			.stream()
			.map(auth -> RoleAuthPo.of(rolePo.getId(), auth))
			.collect(Collectors.toList());

		roleAuthDao.deleteByRoleId(rolePo.getId());
		roleAuthDao.saveBatch(roleAuthPos);

		role.setId(rolePo.getId());
	}
}
