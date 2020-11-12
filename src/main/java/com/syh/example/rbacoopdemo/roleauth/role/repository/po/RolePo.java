package com.syh.example.rbacoopdemo.roleauth.role.repository.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("role")
public class RolePo {

	@TableId(value = "id")
	private Long id;

	@TableField(value = "company_id")
	private String companyId;

	@TableField(value = "name")
	private String name;
}
