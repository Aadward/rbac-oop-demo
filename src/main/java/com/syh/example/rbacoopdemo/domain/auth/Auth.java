package com.syh.example.rbacoopdemo.domain.auth;

import com.syh.example.rbacoopdemo.domain.menu.Menu;
import com.syh.example.rbacoopdemo.domain.operation.Operation;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/10
 **/
public class Auth {

	private AuthType type;

	/**
	 * If {@link AuthType#MENU}, this is a {@link com.syh.example.rbacoopdemo.domain.menu.Menu}
	 * If {@link AuthType#OPERATION}, this is a {@link com.syh.example.rbacoopdemo.domain.operation.Operation}
	 * If {@link AuthType#PRIVILEGED}, this is null
	 */
	private Object value;

	public Auth(AuthType type, Object value) {
		this.type = type;
		this.value = value;
	}

	public boolean contains(Auth other) {
		if (type == AuthType.MENU && other.type == AuthType.MENU) {
			return ((Menu)value).contains((Menu)other.value);
		} else if (type == AuthType.OPERATION && other.type == AuthType.OPERATION) {
			return this.value == other.value;
		} else if (type == AuthType.PRIVILEGED) {
			return true;
		} else {
			return false;
		}
	}

	public String key() {
		return AuthKeyManager.getKey(this);
	}

	public static Auth fromKey(String key) {
		return AuthKeyManager.getAuthFromKey(key);
	}

	private static class AuthKeyManager {

		public static String getKey(Auth auth) {
			if (auth.type == AuthType.MENU) {
				return AuthType.MENU.name() + "|" + ((Menu)auth.value).name();
			} else if (auth.type == AuthType.OPERATION) {
				return AuthType.OPERATION.name() + "|" + ((Operation)auth.value).name();
			} else {
				// all
				return AuthType.PRIVILEGED.name() + "|";
			}
		}

		public static Auth getAuthFromKey(String key) {
			String[] ss = key.split("\\|");

			switch (AuthType.valueOf(ss[0])) {
				case PRIVILEGED:
					return AuthFactory.privileged();
				case OPERATION:
					return AuthFactory.fromOperation(Operation.valueOf(ss[1]));
				case MENU:
					return AuthFactory.fromMenu(Menu.valueOf(ss[1]));
				default:
					throw new RuntimeException("No this auth type: " + ss[0]);
			}
		}
	}
}
