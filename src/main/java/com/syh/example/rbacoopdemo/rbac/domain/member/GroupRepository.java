package com.syh.example.rbacoopdemo.rbac.domain.member;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends CrudRepository<Group, Long> {

    Optional<Group> findByCompany_IdAndGroupId(String companyId, Long groupId);

    List<Group> findByCompany_IdAndGroupIdIn(String companyId, List<Long> groupIdList);
}
