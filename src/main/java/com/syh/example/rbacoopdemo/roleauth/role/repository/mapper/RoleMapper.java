package com.syh.example.rbacoopdemo.roleauth.role.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syh.example.rbacoopdemo.roleauth.role.repository.po.RolePo;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/12
 **/

@Mapper
public interface RoleMapper extends BaseMapper<RolePo> {

}
