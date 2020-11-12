package com.syh.example.rbacoopdemo.roleauth.role;

import java.util.List;

import com.syh.example.rbacoopdemo.roleauth.auth.Auth;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/12
 **/
@AllArgsConstructor
@Getter
public class Role {

	@Setter
	private Long id;

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
}
