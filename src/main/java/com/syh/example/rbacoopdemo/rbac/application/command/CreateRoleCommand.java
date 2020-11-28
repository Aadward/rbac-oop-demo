package com.syh.example.rbacoopdemo.rbac.application.command;

import com.syh.example.rbacoopdemo.rbac.application.dto.AuthDto;
import lombok.Value;

import java.util.List;

@Value
public class CreateRoleCommand {

    String companyId;

    String roleName;

    List<AuthDto> authList;
}
