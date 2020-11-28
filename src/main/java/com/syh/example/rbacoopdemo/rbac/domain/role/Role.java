package com.syh.example.rbacoopdemo.rbac.domain.role;

import com.google.common.collect.Sets;
import com.syh.example.rbacoopdemo.rbac.domain.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Embedded
    @AttributeOverride(
            name = "id",
            column = @Column(name = "company_id")
    )
    private Company company;

    private String name;

    @ElementCollection
    @CollectionTable(name = "role_auth",
            joinColumns = @JoinColumn(name = "role_id"))
    @AttributeOverride(
            name = "resource.resourceKey.key",
            column = @Column(name = "resource_key"))
    Set<Auth> authList = Sets.newHashSet();

    public void giveAuth(Auth auth) {
        this.authList.add(auth);
    }

    public void removeAuth(Auth auth) {
        this.authList.remove(auth);
    }

    private Role(Company company, String name) {
        this.company = company;
        this.name = name;
    }

    public static Role newRole(String companyId, String name) {
        return new Role(new Company(companyId), name);
    }

    public boolean hasAuth(Resource resource, AuthType authType) {
        for (Auth auth : authList) {
            if (auth.hasAuth(new Auth(authType, resource))) {
                return true;
            }
        }
        return false;
    }

    public String getCompanyId() {
        return this.company.getId();
    }
}
