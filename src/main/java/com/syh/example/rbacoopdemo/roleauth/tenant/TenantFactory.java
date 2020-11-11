package com.syh.example.rbacoopdemo.roleauth.tenant;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/11
 **/
public class TenantFactory {

	public static Tenant create(String tenantKey, String tenantName) {
		return new Tenant(tenantKey, tenantName);
	}
}
