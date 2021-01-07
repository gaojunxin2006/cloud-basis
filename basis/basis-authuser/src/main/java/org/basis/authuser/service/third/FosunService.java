/**
 * 
 */
package org.basis.authuser.service.third;

import org.basis.authuser.client.FosunClient;
import org.basis.authuser.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * @author youpan
 *
 */
@Component
@Slf4j
public class FosunService implements SSOService {
    @Autowired
    private FosunClient fosunClient;

    private final String grantType = "authorization_code";
    @Value("${third.sso.fosun.state:fosun}")
    private String state;
    @Value("${third.sso.fosun.clientId:vendor_gemii}")
    private String clientId;
    @Value("${third.sso.fosun.secret:526cf31ca59e39b17df0f637a400c765}")
    private String secret;
    @Value("${third.sso.fosun.redirectUri:http://admin.qywechat.test.gemii.cc:58080/login/3rd}")
    private String redirectUri;

    @Override
    public String getState() {
        return "fosun";
    }

    @Override
    public String getAccessToken(String code) {
        log.info("state:{},clientId:{},secret:{},redirectUri:{}", state, clientId, secret, redirectUri);
        JSONObject resp = fosunClient.getAccessToken(clientId, redirectUri, secret, grantType, code);
        log.info("fosun resp:{}", resp);
        return resp.getString("access_token");
    }

    @Override
    public UserInfo getUserInfo(String accessToken) {
        JSONObject resp = fosunClient.getUserInfo(accessToken);
        log.info("fosun userInfo resp: {}", resp);
        String username = resp.getString("username");
        String userCname = resp.getString("userCname");
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(username);
        userInfo.setUserCname(userCname);
        return userInfo;
    }

}
