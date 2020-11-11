package com.syh.example.rbacoopdemo.roleauth.auth;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.common.collect.Lists;
import com.syh.example.rbacoopdemo.roleauth.resource.Resource;
import com.syh.example.rbacoopdemo.roleauth.resource.ResourceFactory;
import com.syh.example.rbacoopdemo.roleauth.tenant.Tenant;
import com.syh.example.rbacoopdemo.roleauth.tenant.TenantFactory;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/11
 **/

@RunWith(JUnit4.class)
public class AuthTest {

	@Test
	public void composedAuth_ok() throws Exception {
		Tenant tenant = TenantFactory.create("test", "test");

		Resource resource = ResourceFactory.create(tenant, "menu.security", "Security Menu");

		Auth writeAuth = AuthFactory.normal(tenant, "auth.write", "Write Auth",
			Lists.newArrayList(resource));
		Auth readAuth = AuthFactory.normal(tenant, "auth.read", "Read Auth",
			Lists.newArrayList(resource));
		Auth masterAuth = AuthFactory.composed(tenant, "auth.master", "Master Auth",
			Lists.newArrayList(writeAuth, readAuth));

		assertThat(masterAuth.hasAuth(readAuth, resource)).isTrue();
		assertThat(masterAuth.hasAuth(writeAuth, resource)).isTrue();
	}
}
