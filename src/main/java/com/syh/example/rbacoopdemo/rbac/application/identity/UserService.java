package com.syh.example.rbacoopdemo.rbac.application.identity;

public interface UserService {

    UserInfo findUser(String companyId, Long userId);
}
