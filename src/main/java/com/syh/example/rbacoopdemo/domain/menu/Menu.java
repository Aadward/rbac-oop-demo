package com.syh.example.rbacoopdemo.domain.menu;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/10
 **/
public enum Menu {
	/*
		Basic informations
	 */
	BASIC_INFORMATION_SETTING("menu.basic_information_setting"),
	COMPANY_INFORMATION_MANAGEMENT("menu.compay_information_management"),
	BASIC_INFORMATION("menu.basic_info", BASIC_INFORMATION_SETTING, COMPANY_INFORMATION_MANAGEMENT),

	/*
		Security
	 */
	ACCESS_MANAGEMENT("menu.access_management"),
	SECURITY("menu.security", ACCESS_MANAGEMENT);

	public String menuKey;

	private List<Menu> subMenus;

	Menu(String menuKey, @Nonnull Menu... subMenus) {
		this.menuKey = menuKey;
		this.subMenus = Lists.newArrayList(subMenus);
	}

	Menu(String menuKey) {
		this.menuKey = menuKey;
		this.subMenus = Lists.newArrayList();
	}

	public boolean contains(Menu other) {
		if (this == other) {
			return true;
		} else {
			for (Menu subMenu : subMenus) {
				if (subMenu.contains(other)) {
					return true;
				}
			}
		}
		return false;
	}
}
