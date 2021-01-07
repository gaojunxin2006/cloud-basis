/**
 * Project Name:common-sdk
 * File Name:Message.java
 * Package Name:org.basis.common.sdk.pojo.base
 * Date:Mar 6, 20201:45:10 PM
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.common.sdk.pojo.base;

import org.basis.common.sdk.enumeration.ResultCodeEnum;

import lombok.Data;

/**
 * ClassName:Message <br/>
 * Function: ADD FUNCTION. <br/>
 * Date: Mar 6, 2020 1:45:10 PM <br/>
 * 
 * @author youpan
 * @version
 * @since JDK 1.8
 * @see
 */
@Data
public class Message<E> {
	private int code;
	private String msg;
	private E data;
	private String requestId;
	private PageInfo pageInfo;

	private Message() {
		this.code = ResultCodeEnum.SUCCESS.getCode();
		this.msg = ResultCodeEnum.SUCCESS.getMsg();
	}

	private Message(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private Message(int code, String msg, E data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	private Message(int code, String msg, E data, PageInfo pageInfo) {
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.pageInfo = pageInfo;
	}

	/**
	 * 成功
	 * 
	 * @author youpan
	 * @return
	 */
	public static <E> Message<E> sucess() {
		return new Message<>();
	}

	/**
	 * 返回成功</br>
	 * 固定CODE，描述可变
	 * 
	 * @author youpan
	 * @param description
	 * @return
	 */
	public static <E> Message<E> sucess(String msg) {
		return new Message<>(ResultCodeEnum.SUCCESS.getCode(), msg);
	}

	/**
	 * 返回成功
	 * 
	 * @author youpan
	 * @param <E>
	 * @param data
	 * @return
	 */
	public static <E> Message<E> sucess(E data) {
		return new Message<>(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMsg(), data);
	}

	/**
	 * 返回成功
	 * 
	 * @author youpan
	 * @param <E>
	 * @param data
	 * @return
	 */
	public static <E> Message<E> sucess(E data, PageInfo pageInfo) {
		return new Message<>(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMsg(), data, pageInfo);
	}

	/**
	 * 失败
	 * 
	 * @author youpan
	 * @return
	 */
	public static <E> Message<E> failure() {
		return new Message<>(ResultCodeEnum.UNAVALIABLE.getCode(), ResultCodeEnum.UNAVALIABLE.getMsg());
	}

	/**
	 * 失败</br>
	 * 固定CODE，固定描述
	 * 
	 * @author youpan
	 * @return
	 */
	public static <E> Message<E> failure(ResultCodeEnum resultCodeEnum) {
		return new Message<>(resultCodeEnum.getCode(), resultCodeEnum.getMsg());
	}

	/**
	 * 失败</br>
	 * 固定CODE枚举，描述可变
	 * 
	 * @author youpan
	 * @param codeEnum
	 * @param description
	 * @return
	 */
	public static <E> Message<E> failure(ResultCodeEnum resultCodeEnum, String msg) {
		return new Message<>(resultCodeEnum.getCode(), msg);
	}

	/**
	 * 失败</br>
	 * 自定义描述
	 * 
	 * @author youpan
	 * @param code
	 * @return
	 */
	public static <E> Message<E> failure(String msg) {
		return new Message<>(ResultCodeEnum.FAIL.getCode(), msg);
	}

}
