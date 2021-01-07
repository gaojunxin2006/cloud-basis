package org.basis.authuser.model;

import lombok.Data;

@Data
public class AccessToken {

    private String access_token;

    private Long expires_in;

    private String refresh_token;

    private String token_type;

}
