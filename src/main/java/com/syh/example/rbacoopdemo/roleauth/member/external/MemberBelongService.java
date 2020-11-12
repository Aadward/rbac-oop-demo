package com.syh.example.rbacoopdemo.roleauth.member.external;

import java.util.List;

import com.syh.example.rbacoopdemo.roleauth.member.Member;
import com.syh.example.rbacoopdemo.roleauth.member.MemberKey;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/12
 **/
public interface MemberBelongService {

	List<MemberKey> memberBelongTo(Member member);
}
