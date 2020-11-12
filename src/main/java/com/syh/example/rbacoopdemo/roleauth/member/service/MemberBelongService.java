package com.syh.example.rbacoopdemo.roleauth.member.service;

import java.util.List;

import com.syh.example.rbacoopdemo.roleauth.member.Member;
import com.syh.example.rbacoopdemo.roleauth.member.MemberKey;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/12
 **/
interface MemberBelongService {

	List<MemberKey> memberBelongTo(Member member);
}
