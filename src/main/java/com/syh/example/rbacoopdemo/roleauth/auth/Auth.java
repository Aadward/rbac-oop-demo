package com.syh.example.rbacoopdemo.roleauth.auth;

import java.util.List;

import com.syh.example.rbacoopdemo.roleauth.resource.Resource;
import lombok.Data;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/11
 **/

@Data
public class Auth {

	private Long id;

	private Long tenantId;

	private String authKey;

	private String desc;

	private List<Auth> composedAuthList;

	private List<String> resourcesKeys;

	public Auth(Long tenantId, String authKey, String desc,
		List<Auth> composedAuthList, List<String> resourcesKeys) {
		this.tenantId = tenantId;
		this.authKey = authKey;
		this.desc = desc;
		this.composedAuthList = composedAuthList;
		this.resourcesKeys = resourcesKeys;
	}

	public boolean hasAuth(Auth auth, Resource resource) {
		if (this.equals(auth)) {
			return resourcesKeys.contains(resource.getResourceKey());
		} else {
			for (Auth subAuth : composedAuthList) {
				if (subAuth.hasAuth(auth, resource)) {
					return true;
				}
			}
			return false;
		}
	}
}
