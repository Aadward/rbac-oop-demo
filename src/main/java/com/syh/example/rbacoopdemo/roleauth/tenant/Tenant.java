package com.syh.example.rbacoopdemo.roleauth.tenant;

import lombok.Getter;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/11
 **/

@Getter
public class Tenant {

	private Long tenantId;

	private String tenantKey;

	private String tenantName;

	public Tenant(String tenantKey, String tenantName) {
		this.tenantKey = tenantKey;
		this.tenantName = tenantName;
	}
}
