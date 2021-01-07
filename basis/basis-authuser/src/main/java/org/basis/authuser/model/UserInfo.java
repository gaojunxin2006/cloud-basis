package org.basis.authuser.model;

import lombok.Data;

/**
 * 第三方单点登陆 查询用户登陆信息接收
 */
@Data
public class UserInfo {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String userCname;

    /**
     * 对应企业微信中的user_id
     */
    private String userId;

    /**
     * 对应企业微信中的corp_id
     */
    private String corpId;
}
