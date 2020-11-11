package com.syh.example.rbacoopdemo.roleauth.resource.repo;

import java.util.List;

import com.syh.example.rbacoopdemo.roleauth.resource.Resource;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/11
 **/
public interface ResourceRepository {

	List<Resource> findByTenant(Long tenantId);

	Resource save(Resource resource);
}
