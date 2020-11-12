package com.syh.example.rbacoopdemo.roleauth;

import static org.assertj.core.api.Assertions.*;

import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.syh.example.rbacoopdemo.BaseSpringBootTest;
import com.syh.example.rbacoopdemo.roleauth.auth.Auth;
import com.syh.example.rbacoopdemo.roleauth.member.Member;
import com.syh.example.rbacoopdemo.roleauth.member.MemberType;
import com.syh.example.rbacoopdemo.roleauth.member.repository.MemberRepository;
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

	@Test
	@FlywayTest
	public void authorizeSecurityManagerToUser() throws Exception {
		/*
			Init role: Security Manager
		 */
		Auth securityAuth = Auth.fullAuthForMenu(Menu.SECURITY);
		Role securityManager = new Role(1L, "Security Manager", Lists.newArrayList(securityAuth));
		roleRepository.save(securityManager);

		/*
			Grant role to user
		 */
		Long userId = 1L;
		Member user = new Member(MemberType.USER, userId);
		user.grantRole(securityManager);
		memberRepository.save(user);

		/*
			Check auth for user
		 */
		Member persistUser = memberRepository.selectByTypeAndId(MemberType.USER, userId);
		// has auth for security menu
		assertThat(authService.hasFullAuthForMenu(persistUser, Menu.SECURITY)).isTrue();
		// has auth for sub-menu: security_network_setting
		assertThat(authService.hasFullAuthForMenu(persistUser, Menu.SECURITY_NETWORK_SETTING)).isTrue();
	}
}
