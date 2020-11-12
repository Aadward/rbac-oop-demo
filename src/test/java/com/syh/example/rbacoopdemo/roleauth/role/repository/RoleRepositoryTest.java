package com.syh.example.rbacoopdemo.roleauth.role.repository;

import com.google.common.collect.Lists;
import com.syh.example.rbacoopdemo.BaseSpringBootTest;
import com.syh.example.rbacoopdemo.roleauth.role.Role;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class RoleRepositoryTest extends BaseSpringBootTest {

    @Autowired
    private RoleRepository roleRepository;

    @FlywayTest
    @Test
    public void getByCompany() {
        Role role = Role.create("test_company", "Test Role", Lists.newArrayList());
        roleRepository.save(role);

        assertThat(roleRepository.findByCompanyId("test_company"))
                .size().isEqualTo(1);
    }
}
