package com.syh.example.rbacoopdemo.rbac.domain.member;

import com.google.common.collect.Sets;
import com.syh.example.rbacoopdemo.rbac.domain.Company;
import com.syh.example.rbacoopdemo.rbac.domain.role.Auth;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "member")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "MEMBER_TYPE")
@NoArgsConstructor
public abstract class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    protected Long id;

    @AttributeOverride(
            name = "id",
            column = @Column(name = "company_id")
    )
    protected Company company;

    @ElementCollection
    @CollectionTable(name = "member_role",
            joinColumns = @JoinColumn(name = "member_id"))
    @Column(name = "role_id")
    @Getter
    protected Set<Long> roleIds = Sets.newHashSet();

    public Member(Company company) {
        this.company = company;
    }

    public void grantRole(Long roleId) {
        this.roleIds.add(roleId);
    }

    public void removeRole(Long roleId) {
        this.roleIds.remove(roleId);
    }
}
