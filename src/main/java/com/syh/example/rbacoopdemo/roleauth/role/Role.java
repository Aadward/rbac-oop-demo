package com.syh.example.rbacoopdemo.roleauth.role;

import com.google.common.collect.Lists;
import com.syh.example.rbacoopdemo.roleauth.auth.Auth;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author shen.yuhang
 * created on 2020/11/12
 **/
@AllArgsConstructor
@Getter
public class Role {

    @Setter
    private Long id;

    private String companyId;

    private String name;

    private List<Auth> authList;

    public boolean hasAuth(Auth target) {
        for (Auth auth : authList) {
            if (auth.match(target)) {
                return true;
            }
        }
        return false;
    }

    public static Role create(String companyId, String rolename, List<Auth> auth) {
        return new Role(null, companyId, rolename, Lists.newArrayList(auth));
    }
}
