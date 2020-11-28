package com.syh.example.rbacoopdemo.rbac.exception;

public class RoleCanNotFoundException extends RuntimeException {

    public RoleCanNotFoundException(String message) {
        super(message);
    }
}
