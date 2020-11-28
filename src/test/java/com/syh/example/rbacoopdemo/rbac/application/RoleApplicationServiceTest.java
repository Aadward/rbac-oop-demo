package com.syh.example.rbacoopdemo.rbac.application;

import com.google.common.collect.Lists;
import com.syh.example.rbacoopdemo.rbac.application.command.CreateRoleCommand;
import com.syh.example.rbacoopdemo.rbac.application.dto.AuthDto;
import com.syh.example.rbacoopdemo.rbac.domain.role.AuthType;
import com.syh.example.rbacoopdemo.rbac.domain.role.Menu;
import com.syh.example.rbacoopdemo.rbac.domain.role.Resource;
import com.syh.example.rbacoopdemo.rbac.domain.role.ResourceType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RoleApplicationServiceTest {

    private static final String COMPANY_ID = "company-id";

    @Autowired
    private RoleApplicationService roleApplicationService;

    @Test
    public void roleApplicationTest() {

        // first, make sure there is not any role
        assertThat(roleApplicationService.allRolesOfCompany(COMPANY_ID)).isEmpty();

        // create one role with SECURITY_NETWORK_SETTING auth
        Long roleId = roleApplicationService.createRole(
                new CreateRoleCommand(COMPANY_ID, "my-role",
                        Lists.newArrayList(
                                new AuthDto(
                                        ResourceType.MENU.name(),
                                        Menu.SECURITY_NETWORK_SETTING.name(),
                                        AuthType.FULL.name()
                                )
                        )))
                .getId();

        // assert auth
        assertThat(roleApplicationService.find(COMPANY_ID, roleId).hasAuth(Resource.menu(Menu.SECURITY_NETWORK_SETTING), AuthType.FULL)).isTrue();
        assertThat(roleApplicationService.find(COMPANY_ID, roleId).hasAuth(Resource.menu(Menu.SECURITY), AuthType.FULL)).isFalse();


        // give SECURITY auth, remove SECURITY_NETWORK_SETTING auth
        roleApplicationService.grantAuth(COMPANY_ID, roleId,
                new AuthDto(
                        ResourceType.MENU.name(),
                        Menu.SECURITY.name(),
                        AuthType.FULL.name()
                )
        );
        roleApplicationService.removeAuth(
                COMPANY_ID,
                roleId,
                new AuthDto(
                        ResourceType.MENU.name(),
                        Menu.SECURITY_NETWORK_SETTING.name(),
                        AuthType.FULL.name()
                )
        );

        // assert auth
        assertThat(roleApplicationService.find(COMPANY_ID, roleId).hasAuth(Resource.menu(Menu.SECURITY_NETWORK_SETTING), AuthType.FULL)).isTrue();
        assertThat(roleApplicationService.find(COMPANY_ID, roleId).hasAuth(Resource.menu(Menu.SECURITY), AuthType.FULL)).isTrue();

        // delete role
        roleApplicationService.deleteRole(COMPANY_ID, roleId);

        // make sure there is not any role
        assertThat(roleApplicationService.allRolesOfCompany("company-id")).isEmpty();
    }
}
