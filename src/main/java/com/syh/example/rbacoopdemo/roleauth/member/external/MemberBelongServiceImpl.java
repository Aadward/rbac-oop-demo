package com.syh.example.rbacoopdemo.roleauth.member.external;

import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.syh.example.rbacoopdemo.roleauth.member.Member;
import com.syh.example.rbacoopdemo.roleauth.member.MemberKey;
import com.syh.example.rbacoopdemo.roleauth.member.MemberType;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/12
 **/
@Component
class MemberBelongServiceImpl implements MemberBelongService {

	/**
	 *	exclude itself
	 */
	public List<MemberKey> memberBelongTo(Member member) {
		List<MemberKey> members = Lists.newArrayList();

		getGroupIds()
			.stream()
			.map(id -> new MemberKey(member.getCompanyId(), MemberType.GROUP, id))
			.forEach(members::add);

		getDepts()
			.stream()
			.map(id -> new MemberKey(member.getCompanyId(), MemberType.DEPT, id))
			.forEach(members::add);

		return members;
	}

	private List<Long> getGroupIds() {
		// get groups
		return Lists.newArrayList();
	}

	private List<Long> getDepts() {
		// get depts
		return Lists.newArrayList();
	}
}
