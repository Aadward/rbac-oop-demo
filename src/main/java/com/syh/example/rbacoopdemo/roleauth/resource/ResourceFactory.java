package com.syh.example.rbacoopdemo.roleauth.resource;

import com.syh.example.rbacoopdemo.roleauth.tenant.Tenant;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/11
 **/
public class ResourceFactory {

	public static Resource create(Tenant tenant, String resourceKey, String resourceName) {
		return new Resource(tenant.getTenantId(), resourceKey, resourceName);
	}
}
