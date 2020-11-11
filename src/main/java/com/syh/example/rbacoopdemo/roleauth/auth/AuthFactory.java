package com.syh.example.rbacoopdemo.roleauth.auth;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.syh.example.rbacoopdemo.roleauth.resource.Resource;
import com.syh.example.rbacoopdemo.roleauth.tenant.Tenant;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/11
 **/
public class AuthFactory {

	public static Auth normal(Tenant tenant, String authKey, String desc, List<Resource> resources) {
		return new Auth(
			tenant.getTenantId(),
			authKey,
			desc,
			Lists.newArrayList(),
			resources
				.stream()
				.map(Resource::getResourceKey)
				.collect(Collectors.toList()));
	}

	public static Auth composed(Tenant tenant, String authKey, String desc, List<Auth> auths) {
		return new Auth(
			tenant.getTenantId(),
			authKey,
			desc,
			auths,
			Lists.newArrayList());
	}
}
