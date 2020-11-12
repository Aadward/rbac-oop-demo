package com.syh.example.rbacoopdemo.roleauth.resource;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/12
 **/
public enum Operation {
	;
	private String resourceKey;

	Operation(String resourceKey) {
		this.resourceKey = resourceKey;
	}
}
