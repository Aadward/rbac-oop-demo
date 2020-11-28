package com.syh.example.rbacoopdemo.rbac.domain.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Auth {

    @Enumerated
    private AuthType authType;

    private Resource resource;

    public boolean hasAuth(Auth that) {
        return this.authType == that.authType && this.resource.include(that.resource);
    }
}
