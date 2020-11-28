package com.syh.example.rbacoopdemo.rbac.domain.member;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeptRepository extends CrudRepository<Dept, Long> {

    Optional<Dept> findByCompany_IdAndDeptId(String companyId, Long deptId);

    List<Dept> findByCompany_IdAndDeptIdIn(String companyId, List<Long> deptIdList);
}
