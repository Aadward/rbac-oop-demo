package com.syh.example.rbacoopdemo.roleauth.resource;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.syh.example.rbacoopdemo.roleauth.tenant.Tenant;
import com.syh.example.rbacoopdemo.roleauth.tenant.TenantFactory;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/11
 **/

@RunWith(JUnit4.class)
public class ResourceTest {

	private Tenant tenant = TenantFactory.create("test", "test");

	@Test
	public void resource_contains() throws Exception {
		Resource resource = ResourceFactory.create(tenant, "resource_1", "resource 1");
		Resource resource2 = ResourceFactory.create(tenant, "resource_2", "resource 2");

		resource2.setParent(resource);

		assertThat(resource.contains(resource2)).isTrue();
	}

	@Test
	public void resource_notContains() throws Exception {
		Resource resource = ResourceFactory.create(tenant, "resource_1", "resource 1");
		Resource resource2 = ResourceFactory.create(tenant, "resource_2", "resource 2");

		assertThat(resource.contains(resource2)).isFalse();
	}
}
