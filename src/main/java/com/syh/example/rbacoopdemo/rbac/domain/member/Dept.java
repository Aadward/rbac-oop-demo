package com.syh.example.rbacoopdemo.rbac.domain.member;

import com.syh.example.rbacoopdemo.rbac.domain.Company;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DEPT")
@NoArgsConstructor
public class Dept extends Member {

    @Column(name = "org_member_id")
    private Long deptId;

    private Dept(Company company, Long deptId) {
        super(company);
        this.deptId = deptId;
    }

    public static Dept of(String companyId, Long deptId) {
        return new Dept(new Company(companyId), deptId);
    }
}
