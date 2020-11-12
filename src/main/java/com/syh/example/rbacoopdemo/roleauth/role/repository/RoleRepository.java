package com.syh.example.rbacoopdemo.roleauth.role.repository;

import com.google.common.collect.Lists;
import com.syh.example.rbacoopdemo.roleauth.auth.Auth;
import com.syh.example.rbacoopdemo.roleauth.role.Role;
import com.syh.example.rbacoopdemo.roleauth.role.repository.mapper.RoleAuthDao;
import com.syh.example.rbacoopdemo.roleauth.role.repository.mapper.RoleDao;
import com.syh.example.rbacoopdemo.roleauth.role.repository.po.RoleAuthPo;
import com.syh.example.rbacoopdemo.roleauth.role.repository.po.RolePo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shen.yuhang
 * created on 2020/11/12
 **/

@Repository
@RequiredArgsConstructor
public class RoleRepository {

    private final RoleDao roleDao;
    private final RoleAuthDao roleAuthDao;

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

    public List<Role> findByIdList(List<Long> roleIds) {
        if (roleIds.isEmpty()) {
            return Lists.newArrayList();
        }

        List<RolePo> rolePos = roleDao.listByIds(roleIds);
        return convertPoToRole(rolePos);
    }

    public List<Role> findByCompanyId(String companyId) {
        List<RolePo> rolePos = roleDao.query()
                .eq("company_id", companyId)
                .list();

        return convertPoToRole(rolePos);
    }

    private List<Role> convertPoToRole(List<RolePo> pos) {
        return pos.stream()
                .map(po -> {
                    List<Auth> authList = roleAuthDao.selectByRoleId(po.getId())
                            .stream()
                            .map(RoleAuthPo::toAuth)
                            .collect(Collectors.toList());

                    return new Role(po.getId(), po.getCompanyId(), po.getName(), authList);
                })
                .collect(Collectors.toList());
    }
}
