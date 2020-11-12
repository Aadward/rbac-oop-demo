package com.syh.example.rbacoopdemo.roleauth.resource;

import java.util.Arrays;

import lombok.Getter;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/12
 **/

public enum Menu {

	SECURITY("menu.security"),
	SECURITY_NETWORK_SETTING("menu.security.network_setting", SECURITY);

	@Getter
	private String resourceKey;

	private Menu parent;

	Menu(String resourceKey) {
		this.resourceKey = resourceKey;
	}

	Menu(String resourceKey, Menu parent) {
		this.resourceKey = resourceKey;
		this.parent = parent;
	}

	public static Menu fromResourceKey(String resourceKey) {
		return Arrays.stream(values())
			.filter(menu -> menu.resourceKey.equals(resourceKey))
			.findFirst()
			.orElseThrow(() -> new RuntimeException("Can not find this menu: " + resourceKey));
	}

	public boolean include(Menu menu) {
		if (menu == this) {
			return true;
		}
		if (menu.parent != null) {
			return include(menu.parent);
		}
		return false;
	}
}
