package org.basis.authuser.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.basis.authuser.model.AccessToken;
import org.basis.authuser.model.UserInfo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;

@Service
@Slf4j
public class SSORequestService {


    @Resource
    private RestTemplate restTemplate;

    private String accessTokenUrl = "https://auth.imp.fosun.com/oauth/token";
    private String userInfoUrl= "https://auth.imp.fosun.com/third/oauth2/userinfo";
    private String returnUrl = "http://admin.qywechat.test.gemii.cc:58080/login";



    /**
     * @param type  0:获取token 1:刷新token
     * @param code  第三方校验过后给的授权码 tips：经过第三方302之后可以拿到
     * @param state 我们自定义的可以用来判断是哪个渠道的
     */
    public AccessToken getAccessToken(int type,String code,String state) throws URISyntaxException {
        //todo:根据state我们可以去取appId以及appSecret，进行后续验证
        String clientSecret="appSecret";
        String clientId="appId";
        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        postParameters.add("client_id", clientId);
        postParameters.add("client_secret", clientSecret);

        if(type==0){
            //0 获取token
            postParameters.add("redirect_uri", returnUrl);
            postParameters.add("grant_type", "authorization_code");
            postParameters.add("code", code);
        }else{
            //1 刷新token
            postParameters.add("grant_type", "refresh_token");
            postParameters.add("refresh_token", code);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, headers);
        URI url = new URI(accessTokenUrl);
        String json=restTemplate.postForObject(url, r, String.class);
        AccessToken accessToken=JSONObject.parseObject(json, AccessToken.class);
        return accessToken;
    }

    /**
     * sso 根据accessToken 获取用户信息
     */
    public UserInfo getUserInfo(String accessToken) throws URISyntaxException{
        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        postParameters.add("access_token", accessToken);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, headers);
        URI url = new URI(userInfoUrl);
        String json=restTemplate.postForObject(url, r, String.class);
        UserInfo userInfo=JSONObject.parseObject(json, UserInfo.class);
        return userInfo;
    }

    /**
     * 匹配用户
     */
    public void matchUser(UserInfo userInfo){

    }

}
