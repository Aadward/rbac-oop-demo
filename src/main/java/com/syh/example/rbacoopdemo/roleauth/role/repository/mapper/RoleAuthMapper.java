package com.syh.example.rbacoopdemo.roleauth.role.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syh.example.rbacoopdemo.roleauth.role.repository.po.RoleAuthPo;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/12
 **/

@Mapper
public interface RoleAuthMapper extends BaseMapper<RoleAuthPo> {
}
