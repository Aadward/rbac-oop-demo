package com.syh.example.rbacoopdemo.rbac.domain.member;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberPersistenceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void saveMember() throws Exception {
        User user = User.of("company-id", 1L);
        Group group = Group.of("company-id", 1L);
        Dept dept = Dept.of("dept-id", 1L);


        entityManager.persist(user);
        entityManager.persist(group);
        entityManager.persist(dept);

        List<Member> members = Lists.newArrayList(memberRepository.findAll());
        assertThat(members).size().isEqualTo(3);
    }

    @Test
    public void grantRole() throws Exception {
        User user = User.of("company-id", 1L);
        entityManager.persist(user);

        // grant role to user
        user.grantRole(1L);

        assertThat(entityManager.find(Member.class, user.id).getRoleIds()).size().isEqualTo(1);
        assertThat(memberRepository.findById(user.id)).hasValueSatisfying(obj -> {
            assertThat(obj.getRoleIds()).size().isEqualTo(1);
        });

        // then delete role
        user.removeRole(1L);

        assertThat(entityManager.find(Member.class, user.id).getRoleIds()).size().isEqualTo(0);
        assertThat(memberRepository.findById(user.id)).hasValueSatisfying(obj -> {
            assertThat(obj.getRoleIds()).size().isEqualTo(0);
        });
    }
}
