package com.syh.example.rbacoopdemo.roleauth.role;

import java.util.List;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/11
 **/
public class Role {

	private Long roleId;

	private Long tenantId;

	private String roleKey;

	private List<Long> authIds;
}
