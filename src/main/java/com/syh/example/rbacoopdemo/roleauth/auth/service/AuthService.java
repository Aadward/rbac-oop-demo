package com.syh.example.rbacoopdemo.roleauth.auth.service;

import com.syh.example.rbacoopdemo.roleauth.auth.Auth;
import com.syh.example.rbacoopdemo.roleauth.resource.Resource;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/11
 **/
public class AuthService {

	public boolean hasAuth(Auth auth, Resource resource) {
		if (auth.getResourcesKeys().contains(resource.getResourceKey())) {
			return true;
		}

		for (Auth auth1 : auth.getComposedAuthList()) {
			if (hasAuth(auth1, resource)) {
				return true;
			}
		}

		return false;
	}
}
