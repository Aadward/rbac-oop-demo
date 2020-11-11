package com.syh.example.rbacoopdemo.roleauth.resource;

import lombok.Data;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/11
 **/

@Data
public class Resource {

	private Long resourceId;

	private Long tenantId;

	private String resourceKey;

	private String resourceName;

	/** null means no parent **/
	private Resource parent;

	public Resource(Long tenantId, String resourceKey, String resourceName) {
		this.tenantId = tenantId;
		this.resourceKey = resourceKey;
		this.resourceName = resourceName;
	}

	public void setParent(Resource parent) {
		this.parent = parent;
	}

	public boolean contains(Resource other) {
		Resource child = other;

		while (child != null) {
			if (this.equals(child.getParent())) {
				return true;
			}
			child = child.getParent();
		}

		return false;
	}

}
