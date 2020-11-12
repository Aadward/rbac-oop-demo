package com.syh.example.rbacoopdemo.roleauth.auth;

import com.syh.example.rbacoopdemo.roleauth.resource.Menu;
import com.syh.example.rbacoopdemo.roleauth.resource.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/12
 **/

@AllArgsConstructor
@Data
public class Auth {

	private AuthType authType;

	private Resource resource;

	public boolean match(Auth that) {
		return this.authType == that.authType && this.resource.match(that.resource);
	}

	public static Auth fullAuthFor(Resource resource) {
		return new Auth(AuthType.FULL, resource);
	}

	public static Auth fullAuthForMenu(Menu menu) {
		return fullAuthFor(Resource.fromMenu(menu));
	}
}
