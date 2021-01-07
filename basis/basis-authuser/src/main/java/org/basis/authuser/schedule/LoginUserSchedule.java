/**
 * Project Name:basis-authuser
 * File Name:LoginUserSchedule.java
 * Package Name:org.basis.authuser.schedule
 * Date:Apr 6, 20201:22:44 PM
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.authuser.schedule;

import org.basis.authuser.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;

/**
 * ClassName:LoginUserSchedule <br/>
 * Function: ADD FUNCTION. <br/>
 * Date: Apr 6, 2020 1:22:44 PM <br/>
 * 
 * @author youpan
 * @version
 * @since JDK 1.8
 * @see
 */
@Component
public class LoginUserSchedule {
	@Autowired
	private LoginUserService loginUserService;

	/**
	 * 由调度中心定时更新用户/企业登录状态<br>
	 * 推荐每半小时刷新一次
	 * 
	 * @author youpan
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@XxlJob("deleteLogoutUser")
	public ReturnT<String> deleteLogoutUser(String param) throws Exception {
		loginUserService.deleteLogoutUserCorp();
		return ReturnT.SUCCESS;
	}
}
