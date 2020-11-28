package com.syh.example.rbacoopdemo.rbac.domain.member;

import com.syh.example.rbacoopdemo.rbac.domain.Company;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("GROUP")
@NoArgsConstructor
public class Group extends Member {

    @Column(name = "org_member_id")
    private Long groupId;

    private Group(Company company, Long groupId) {
        super(company);
        this.groupId = groupId;
    }

    public static Group of(String companyId, Long groupId) {
        return new Group(new Company(companyId), groupId);
    }
}
