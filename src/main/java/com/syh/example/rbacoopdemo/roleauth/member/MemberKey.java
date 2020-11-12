package com.syh.example.rbacoopdemo.roleauth.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberKey {

	private String companyId;

	private MemberType memberType;

	private Long orgMemberId;
}
