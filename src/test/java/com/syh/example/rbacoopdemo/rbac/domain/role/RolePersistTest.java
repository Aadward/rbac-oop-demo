package com.syh.example.rbacoopdemo.rbac.domain.role;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RolePersistTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void saveRole() throws Exception {
        Role role = Role.newRole("company-id", "testRole");

        entityManager.persist(role);

        // same auth, should only append one
        role.giveAuth(new Auth(AuthType.FULL, Resource.menu(Menu.SECURITY)));
        role.giveAuth(new Auth(AuthType.FULL, Resource.menu(Menu.SECURITY)));

        assertThat(roleRepository.findById(role.getId()))
                .hasValueSatisfying(obj -> {
                    assertThat(role.authList).size().isEqualTo(1);
                });

        role.removeAuth(new Auth(AuthType.FULL, Resource.menu(Menu.SECURITY)));
        assertThat(roleRepository.findById(role.getId()))
                .hasValueSatisfying(obj -> {
                    assertThat(role.authList).size().isEqualTo(0);
                });
    }
}
