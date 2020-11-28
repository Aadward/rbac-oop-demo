package com.syh.example.rbacoopdemo.rbac.domain.role;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class RoleTest {

    @Test
    public void role_authTest() {
        Role role = Role.newRole("company-id", "security manager");
        role.giveAuth(new Auth(AuthType.FULL, Resource.menu(Menu.SECURITY)));


        assertThat(role.hasAuth(Resource.menu(Menu.SECURITY), AuthType.FULL)).isTrue();
        assertThat(role.hasAuth(Resource.menu(Menu.SECURITY_NETWORK_SETTING), AuthType.FULL)).isTrue();
    }
}
