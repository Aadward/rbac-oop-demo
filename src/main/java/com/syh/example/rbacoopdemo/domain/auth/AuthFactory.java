package com.syh.example.rbacoopdemo.domain.auth;

import com.syh.example.rbacoopdemo.domain.menu.Menu;
import com.syh.example.rbacoopdemo.domain.operation.Operation;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/10
 **/

public class AuthFactory {

	public static Auth fromMenu(Menu menu) {
		return new Auth(AuthType.MENU, menu);
	}

	public static Auth privileged() {
		return new Auth(AuthType.PRIVILEGED, null);
	}

	public static Auth fromOperation(Operation operation) {
		return new Auth(AuthType.OPERATION, operation);
	}

}
