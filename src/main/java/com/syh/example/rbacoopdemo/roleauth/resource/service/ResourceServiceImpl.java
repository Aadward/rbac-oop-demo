package com.syh.example.rbacoopdemo.roleauth.resource.service;

import com.syh.example.rbacoopdemo.roleauth.resource.Resource;
import com.syh.example.rbacoopdemo.roleauth.resource.service.ResourceService;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/11
 **/
public class ResourceServiceImpl implements ResourceService {

	@Override
	public boolean contains(Resource r1, Resource r2) {
		Resource child = r2;

		while (child != null) {
			if (r1.equals(child.getParent())) {
				return true;
			}
			child = child.getParent();
		}

		return false;
	}
}
