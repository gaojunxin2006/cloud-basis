/**
 * 
 */
package org.basis.authuser.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;

/**
 * @author youpan
 *
 */
@FeignClient(name = "fosunClient", url = "https://auth.imp.fosun.com")
public interface FosunClient {

    /**
     * 获取 AccessToken
     * 
     * @param clientId     申请应用时分配的 APP ID
     * @param redirectUri  授权回调地址, 必须和申请应 用是填写的一致(参数部分可不 一致)
     * @param clientSecret 申请应用时分配的 App Secret
     * @param grantType    授权类型，为固定 “authorization_code”。
     * @param code         拿到的授权码
     * @return { "access_token": "eyJhbGciOiJSU*****P2Fo5wdM8w",
     *         "token_type":"bearer", "refresh_token": "eyJhbG*******lJAy0g",
     *         "expires_in": 3599}
     * @author youpan
     */
    @PostMapping("/oauth/token")
    JSONObject getAccessToken(@RequestParam("client_id") String clientId,
            @RequestParam("redirect_uri") String redirectUri, @RequestParam("client_secret") String clientSecret,
            @RequestParam("grant_type") String grantType, @RequestParam("code") String code);

    /**
     * 获取用户信息
     * 
     * @param accessToken
     * @return 授权令牌，Access_Token
     * @author youpan
     */
    @PostMapping("/third/oauth2/userinfo")
    JSONObject getUserInfo(@RequestParam("access_token") String accessToken);

}
