package com.syh.example.rbacoopdemo.roleauth.role.repository.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.syh.example.rbacoopdemo.roleauth.auth.Auth;
import com.syh.example.rbacoopdemo.roleauth.auth.AuthType;
import com.syh.example.rbacoopdemo.roleauth.resource.Resource;
import com.syh.example.rbacoopdemo.roleauth.resource.ResourceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/12
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("role_auth")
public class RoleAuthPo {

	@TableId
	private Long id;

	@TableField(value = "role_id")
	private Long roleId;

	@TableField(value = "auth_type")
	private String authType;

	@TableField(value = "resource_type")
	private String resourceType;

	@TableField(value = "resource_key")
	private String resourceKey;

	public Auth toAuth() {
		Resource resource = new Resource(ResourceType.valueOf(resourceType), resourceKey);
		return new Auth(AuthType.valueOf(authType), resource);
	}

	public static RoleAuthPo of(Long roleId, Auth auth) {
		return new RoleAuthPo(null,
			roleId,
			auth.getAuthType().name(),
			auth.getResource().getResourceType().name(),
			auth.getResource().getResourceKey());
	}
}
