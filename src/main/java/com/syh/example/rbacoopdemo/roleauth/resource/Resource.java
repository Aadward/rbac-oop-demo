package com.syh.example.rbacoopdemo.roleauth.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/12
 **/
@EqualsAndHashCode
@Data
public class Resource {

	private ResourceType resourceType;

	private String resourceKey;

	public Resource(ResourceType resourceType, String resourceKey) {
		this.resourceType = resourceType;
		this.resourceKey = resourceKey;
	}

	public static Resource fromMenu(Menu menu) {
		return new Resource(ResourceType.MENU, menu.getResourceKey());
	}

	public boolean match(Resource that) {
		if (resourceType != that.resourceType) {
			return false;
		}
		if (resourceType == ResourceType.MENU) {
			return Menu.fromResourceKey(this.resourceKey).include(Menu.fromResourceKey(that.resourceKey));
		} else {
			return resourceKey.equals(that.resourceKey);
		}
	}
}
