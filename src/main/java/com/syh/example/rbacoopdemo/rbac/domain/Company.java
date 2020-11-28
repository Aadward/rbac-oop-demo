package com.syh.example.rbacoopdemo.rbac.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Company {

    @Getter
    private String id;
}
