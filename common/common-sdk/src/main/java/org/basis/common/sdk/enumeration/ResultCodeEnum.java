/**
 * Project Name:common-sdk
 * File Name:ResultCode.java
 * Package Name:org.basis.common.sdk.enumeration
 * Date:Mar 6, 20201:52:36 PM
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.common.sdk.enumeration;

/**
 * ClassName:ResultCode <br/>
 * Function: 结果枚举 <br/>
 * Date: Mar 6, 2020 1:52:36 PM <br/>
 * 
 * @author youpan
 * @version
 * @since JDK 1.8
 * @see
 */
public enum ResultCodeEnum {
	SUCCESS(100, "成功"), UNAVALIABLE(101, "服务不可用！"), FREQUENT(102, "操作太频繁！"), RESOURCE_ERROR(103, "资源获取失败"), RESOURCE_NOT_ENOUGH(104, "资源不够"),
	UPLOAD_FILE_UNAVALIABLE(105, "文件上传失败"), PARAM_CHECK_ERROR(106, "参数不正确"), CONTENT_CHECK_UNAVALIABLE(107, "内容检查失败"), SERVICE_EXCEPTION(108, "服务异常"),
	FAIL(109, "操作失败"), UNAUTHORIZED(110, "未登录或token过期");

	private int code;
	private String msg;

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	private ResultCodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
