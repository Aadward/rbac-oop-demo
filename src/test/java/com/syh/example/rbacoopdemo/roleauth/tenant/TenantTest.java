package com.syh.example.rbacoopdemo.roleauth.tenant;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/11
 **/

@RunWith(JUnit4.class)
public class TenantTest {

	@Test
	public void createTenant() throws Exception {
		Tenant tenant = TenantFactory.create("test_tenant", "Test Tenant");
		assertThat(tenant).isNotNull();
	}
}
