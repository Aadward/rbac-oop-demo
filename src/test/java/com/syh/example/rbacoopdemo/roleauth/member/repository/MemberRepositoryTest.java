package com.syh.example.rbacoopdemo.roleauth.member.repository;

import com.google.common.collect.Lists;
import com.syh.example.rbacoopdemo.BaseSpringBootTest;
import com.syh.example.rbacoopdemo.roleauth.member.Member;
import com.syh.example.rbacoopdemo.roleauth.member.MemberType;
import com.syh.example.rbacoopdemo.roleauth.role.Role;
import com.syh.example.rbacoopdemo.roleauth.role.repository.RoleRepository;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class MemberRepositoryTest extends BaseSpringBootTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Test
    @FlywayTest
    public void findByRole() throws Exception {
        Role role = Role.create("test_company", "test role", Lists.newArrayList());
        roleRepository.save(role);

        Member userMember = Member.create("test_company", MemberType.USER, 1L);
        userMember.grantRole(role);

        Member groupMember = Member.create("test_company", MemberType.GROUP, 1L);
        groupMember.grantRole(role);

        Member deptMember = Member.create("test_company", MemberType.DEPT, 1L);
        deptMember.grantRole(role);

        memberRepository.save(userMember);
        memberRepository.save(groupMember);
        memberRepository.save(deptMember);

        List<Member> members = memberRepository.selectByRole(role.getId());
        assertThat(members).size().isEqualTo(3);
    }

    @Test
    @FlywayTest
    public void findByRole_empty() throws Exception {
        assertThat(memberRepository.selectByRole(1L)).size().isEqualTo(0);
    }
}
