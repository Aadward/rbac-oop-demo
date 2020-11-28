package com.syh.example.rbacoopdemo.rbac.application.dto;

import com.syh.example.rbacoopdemo.rbac.domain.role.*;
import lombok.Value;

@Value
public class AuthDto {

    String resourceType;

    String resourceKey;

    String authType;

    public Auth toAuth() {
        return new Auth(
                AuthType.valueOf(getAuthType()),
                new Resource(
                        ResourceType.valueOf(getResourceType()),
                        new ResourceKey(getResourceKey())
                )
        );
    }

    public static AuthDto fromAuth(Auth auth) {
        return new AuthDto(auth.getResource().getResourceType().name(),
                auth.getResource().getResourceKey().getKey(),
                auth.getAuthType().name());
    }
}
