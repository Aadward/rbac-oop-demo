package com.syh.example.rbacoopdemo.rbac.domain.member;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByCompany_IdAndUserId(String companyId, Long userId);
}
