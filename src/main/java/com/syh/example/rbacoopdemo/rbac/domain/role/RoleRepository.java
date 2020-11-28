package com.syh.example.rbacoopdemo.rbac.domain.role;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    List<Role> findByCompany_Id(String companyId);

    Optional<Role> findByIdAndCompany_Id(Long id, String companyId);
}
