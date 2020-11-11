package com.syh.example.rbacoopdemo.roleauth.resource.service;

import com.syh.example.rbacoopdemo.roleauth.resource.Resource;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/11
 **/
public interface ResourceService {

	boolean contains(Resource r1, Resource r2);
}
