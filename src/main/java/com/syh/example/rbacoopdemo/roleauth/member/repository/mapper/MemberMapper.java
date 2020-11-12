package com.syh.example.rbacoopdemo.roleauth.member.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syh.example.rbacoopdemo.roleauth.member.repository.po.MemberPo;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/12
 **/
@Mapper
public interface MemberMapper extends BaseMapper<MemberPo> {
}
