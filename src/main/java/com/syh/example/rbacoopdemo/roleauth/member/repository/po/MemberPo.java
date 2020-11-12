package com.syh.example.rbacoopdemo.roleauth.member.repository.po;

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
@TableName("member")
@AllArgsConstructor
@NoArgsConstructor
public class MemberPo {

	@TableId("id")
	private Long id;

	@TableField("member_type")
	private String memberType;

	@TableField("org_member_id")
	private Long orgMemberId;
}
