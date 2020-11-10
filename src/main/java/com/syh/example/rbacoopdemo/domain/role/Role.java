package com.syh.example.rbacoopdemo.domain.role;

import java.util.List;

import com.google.common.collect.Lists;
import com.syh.example.rbacoopdemo.domain.auth.Auth;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/10
 **/
public class Role {

	private long id;

	private List<String> authKeys;

	public Role() {
		authKeys = Lists.newArrayList();
	}

	public List<String> getAuthKeys() {
		return authKeys;
	}

	public void assignAuth(Auth auth) {
		authKeys.add(auth.key());
	}

	public boolean hasAuth(Auth auth) {
		for (String authKey : authKeys) {
			if (Auth.fromKey(authKey).contains(auth)) {
				return true;
			}
		}
		return false;
	}
}
