/**
 * Project Name:basis-authuser
 * File Name:User.java
 * Package Name:org.basis.authuser.pojo
 * Date:Apr 3, 202011:46:07 AM
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.authuser.pojo;

import java.time.LocalDateTime;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

import lombok.Data;

/**
 * ClassName:User <br/>
 * Function: ADD FUNCTION. <br/>
 * Date: Apr 3, 2020 11:46:07 AM <br/>
 * 
 * @author youpan
 * @version
 * @since JDK 1.8
 * @see
 */
@Data
public class User {
	private String id;

	private String userId;

	private String corpId;

	private String name;

	/**
	 * 别名
	 */
	private String alias;

	/**
	 * 职位
	 */
	private String position;

	/**
	 * 二维码
	 */
	private String qrCode;

	/**
	 * 头像
	 */
	private String avatar;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 1男2女
	 */
	private Integer gender;

	/**
	 * 类型(0:普通用户;1:管理员用户)
	 */
	private Integer type;

	/**
	 * 状态(1:正常;3:异常)
	 */
	private Integer status;

	private LocalDateTime createDate;

	private LocalDateTime updateDate;

	private Set<Integer> department;

	private JSONObject tenant;
	
    /**
     * 对外职位
     */
    private String externalPosition;

	private Set<Integer> roles;
}
