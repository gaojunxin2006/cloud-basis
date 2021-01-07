/**
 * 
 */
package org.basis.authuser.service.third;

import org.basis.authuser.model.UserInfo;

/**
 * @author youpan
 *
 */
public interface SSOService {
    String getState();

    String getAccessToken(String code);

    UserInfo getUserInfo(String accessToken);
}
