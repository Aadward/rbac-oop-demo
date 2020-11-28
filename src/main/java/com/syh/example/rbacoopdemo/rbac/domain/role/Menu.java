package com.syh.example.rbacoopdemo.rbac.domain.role;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Menu {

    SECURITY(null),
    SECURITY_NETWORK_SETTING(SECURITY);

    private Menu parent;

    public boolean isParent(Menu that) {
        Menu tmp = that;

        while (tmp != null) {
            if (tmp == this) {
                return true;
            }
            tmp = tmp.parent;
        }
        return false;
    }
}
