package com.syh.example.rbacoopdemo.rbac.domain.member;

import com.syh.example.rbacoopdemo.rbac.domain.Company;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("USER")
@NoArgsConstructor
public class User extends Member {

    @Column(name = "org_member_id")
    private Long userId;

    private User(Company company, Long userId) {
        super(company);
        this.userId = userId;
    }

    public static User of(String companyId, Long userId) {
        return new User(new Company(companyId), userId);
    }
}
