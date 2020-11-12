package com.syh.example.rbacoopdemo.roleauth;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.google.common.collect.Lists;
import com.syh.example.rbacoopdemo.BaseSpringBootTest;
import com.syh.example.rbacoopdemo.roleauth.auth.Auth;
import com.syh.example.rbacoopdemo.roleauth.member.Member;
import com.syh.example.rbacoopdemo.roleauth.member.MemberKey;
import com.syh.example.rbacoopdemo.roleauth.member.MemberType;
import com.syh.example.rbacoopdemo.roleauth.member.repository.MemberRepository;
import com.syh.example.rbacoopdemo.roleauth.member.service.MemberBelongService;
import com.syh.example.rbacoopdemo.roleauth.member.service.MemberService;
import com.syh.example.rbacoopdemo.roleauth.resource.Menu;
import com.syh.example.rbacoopdemo.roleauth.role.Role;
import com.syh.example.rbacoopdemo.roleauth.role.repository.RoleRepository;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/12
 **/

public class AuthorizeTest extends BaseSpringBootTest {

	@Autowired
	private MemberService authService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private MemberRepository memberRepository;

	@MockBean
	private MemberBelongService memberBelongService;

	@Test
	@FlywayTest
	public void grantSecurityManagerToUser() throws Exception {

		String companyId = "test_company";
		Long userId = 1L;


		/*
			Init role: Security Manager
		 */
		Auth securityAuth = Auth.fullAuthForMenu(Menu.SECURITY);
		Role securityManager = Role.create(companyId, "Security Manager", Lists.newArrayList(securityAuth));
		roleRepository.save(securityManager);

		/*
			Grant role to user
		 */
		Member member = new Member(companyId, MemberType.USER, userId);
		member.grantRole(securityManager);
		memberRepository.save(member);

		/*
			Check auth for user
		 */
		Member persistUser = memberRepository.selectByMemberKey(new MemberKey(companyId, MemberType.USER, userId));
		// has auth for security menu
		assertThat(authService.hasFullAuthForMenu(persistUser, Menu.SECURITY)).isTrue();
		// has auth for sub-menu: security_network_setting
		assertThat(authService.hasFullAuthForMenu(persistUser, Menu.SECURITY_NETWORK_SETTING)).isTrue();
	}

	@Test
	@FlywayTest
	public void grantSecurityManagerToGroup() throws Exception {

		String companyId = "test_company";
		Long userId = 1L;
		Long groupId = 2L;


		/*
			Init role: Security Manager
		 */
		Auth securityAuth = Auth.fullAuthForMenu(Menu.SECURITY);
		Role securityManager = Role.create(companyId, "Security Manager", Lists.newArrayList(securityAuth));
		roleRepository.save(securityManager);

		/*
			Grant role to group
		 */
		Member member = new Member(companyId, MemberType.GROUP, groupId);
		member.grantRole(securityManager);
		memberRepository.save(member);

		/*
			Give user no role
		 */
		Member userMember = new Member(companyId, MemberType.USER, userId);
		memberRepository.save(userMember);


		/*
			Prepare mock
		 */
		MemberKey userKey = new MemberKey(companyId, MemberType.USER, userId);
		MemberKey groupKey = new MemberKey(companyId, MemberType.GROUP, groupId);

		given(memberBelongService.memberBelongTo(any()))
			.willReturn(Lists.newArrayList(groupKey));

		/*
			Check auth for user
		 */
		Member persistUser = memberRepository.selectByMemberKey(userKey);
		// has auth for security menu
		assertThat(authService.hasFullAuthForMenu(persistUser, Menu.SECURITY)).isTrue();
		// has auth for sub-menu: security_network_setting
		assertThat(authService.hasFullAuthForMenu(persistUser, Menu.SECURITY_NETWORK_SETTING)).isTrue();
	}

}
