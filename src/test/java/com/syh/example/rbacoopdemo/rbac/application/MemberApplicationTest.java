package com.syh.example.rbacoopdemo.rbac.application;

import com.google.common.collect.Lists;
import com.syh.example.rbacoopdemo.rbac.application.dto.AuthDto;
import com.syh.example.rbacoopdemo.rbac.application.identity.*;
import com.syh.example.rbacoopdemo.rbac.domain.member.DeptRepository;
import com.syh.example.rbacoopdemo.rbac.domain.member.GroupRepository;
import com.syh.example.rbacoopdemo.rbac.domain.member.UserRepository;
import com.syh.example.rbacoopdemo.rbac.domain.role.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberApplicationTest {

    private static final String COMPANY_ID = "company-id";

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MemberApplicationService memberApplicationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private DeptRepository deptRepository;

    @MockBean
    private UserService userService;

    @MockBean
    private GroupService groupService;

    @MockBean
    private DeptService deptService;

    private Role toAssign;

    private Auth auth = new Auth(
            AuthType.FULL,
            new Resource(
                    ResourceType.MENU,
                    new ResourceKey(Menu.SECURITY.name())));

    @Before
    public void initRole() {
        Role role = Role.newRole(COMPANY_ID, "test-role");
        role.giveAuth(auth);

        entityManager.persist(role);

        toAssign = role;
    }

    @Test
    public void grantRoleToUser() {
        given(userService.findUser(eq(COMPANY_ID), eq(1L)))
                .willReturn(new UserInfo(1L, COMPANY_ID));

        memberApplicationService.grantRoleToUser(COMPANY_ID, 1L, toAssign.getId());


        assertThat(userRepository.findByCompany_IdAndUserId(COMPANY_ID, 1L))
                .hasValueSatisfying(user -> {
                    assertThat(user.getRoleIds()).containsExactly(toAssign.getId());
                });

        // check auth api
        assertThat(memberApplicationService.isUserHasAuth(COMPANY_ID, 1L, AuthDto.fromAuth(auth)))
                .isTrue();
    }

    @Test
    public void grantRoleToGroup() {
        given(groupService.findGroup(eq(COMPANY_ID), eq(1L)))
                .willReturn(new GroupInfo(COMPANY_ID, 1L));

        memberApplicationService.grantRoleToGroup(COMPANY_ID, 1L, toAssign.getId());

        assertThat(groupRepository.findByCompany_IdAndGroupId(COMPANY_ID, 1L))
                .hasValueSatisfying(group -> {
                    assertThat(group.getRoleIds()).containsExactly(toAssign.getId());
                });

        // assume user is in that group, test auth
        given(groupService.findGroupsByUser(eq(COMPANY_ID), eq(1L)))
                .willReturn(Lists.newArrayList(new GroupInfo(COMPANY_ID, 1L)));
        assertThat(memberApplicationService.isUserHasAuth(COMPANY_ID, 1L, AuthDto.fromAuth(auth)))
                .isTrue();
    }

    @Test
    public void grantRoleToDept() {
        given(deptService.findDept(eq(COMPANY_ID), eq(1L)))
                .willReturn(new DeptInfo(COMPANY_ID, 1L));

        memberApplicationService.grantRoleToDept(COMPANY_ID, 1L, toAssign.getId());


        assertThat(deptRepository.findByCompany_IdAndDeptId(COMPANY_ID, 1L))
                .hasValueSatisfying(dept -> {
                    assertThat(dept.getRoleIds()).containsExactly(toAssign.getId());
                });

        // assume user is in that group, test auth
        given(deptService.findDeptsByUser(eq(COMPANY_ID), eq(1L)))
                .willReturn(Lists.newArrayList(new DeptInfo(COMPANY_ID, 1L)));
        assertThat(memberApplicationService.isUserHasAuth(COMPANY_ID, 1L, AuthDto.fromAuth(auth)))
                .isTrue();
    }

}
