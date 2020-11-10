package com.syh.example.rbacoopdemo.domain.role;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.syh.example.rbacoopdemo.domain.auth.AuthFactory;
import com.syh.example.rbacoopdemo.domain.menu.Menu;
import com.syh.example.rbacoopdemo.domain.operation.Operation;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/10
 **/

@RunWith(JUnit4.class)
public class AssignAuthToRoleTest {

	@Test
	public void assignAllAuthToRole() throws Exception {
		Role role = new Role();
		role.assignAuth(AuthFactory.privileged());

		assertThat(role.hasAuth(AuthFactory.privileged())).isTrue();
		assertThat(role.hasAuth(AuthFactory.fromMenu(Menu.SECURITY))).isTrue();
		assertThat(role.hasAuth(AuthFactory.fromMenu(Menu.BASIC_INFORMATION_SETTING))).isTrue();
		assertThat(role.hasAuth(AuthFactory.fromMenu(Menu.BASIC_INFORMATION))).isTrue();
		assertThat(role.hasAuth(AuthFactory.fromOperation(Operation.DELETE_SHARED_FOLDER_PERM))).isTrue();
		assertThat(role.hasAuth(AuthFactory.fromOperation(Operation.TOP_FOLDER_MANAGEMENT))).isTrue();
	}

	@Test
	public void assignSecurityAuthToRole() throws Exception {
		Role role = new Role();
		role.assignAuth(AuthFactory.fromMenu(Menu.SECURITY));

		assertThat(role.hasAuth(AuthFactory.privileged())).isFalse();
		assertThat(role.hasAuth(AuthFactory.fromMenu(Menu.SECURITY))).isTrue();
		assertThat(role.hasAuth(AuthFactory.fromMenu(Menu.BASIC_INFORMATION_SETTING))).isFalse();
		assertThat(role.hasAuth(AuthFactory.fromMenu(Menu.BASIC_INFORMATION))).isFalse();
		assertThat(role.hasAuth(AuthFactory.fromOperation(Operation.DELETE_SHARED_FOLDER_PERM))).isFalse();
		assertThat(role.hasAuth(AuthFactory.fromOperation(Operation.TOP_FOLDER_MANAGEMENT))).isFalse();
	}
}
