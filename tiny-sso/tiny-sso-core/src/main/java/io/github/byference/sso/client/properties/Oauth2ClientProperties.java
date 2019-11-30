package io.github.byference.sso.client.properties;

import lombok.Data;

/**
 * SsoOauth2ClientProperties
 *
 * @author byference
 * @since 2019/11/20
 */
@Data
public class Oauth2ClientProperties {

    /**
     * client-id
     */
    private String clientId = "";

    /**
     * client-secret
     */
    private String clientSecret = "";

    /**
     * user-authorization-uri
     */
    private String userAuthorizationUri = "";

    /**
     * access-token-uri
     */
    private String accessTokenUri = "";

    /**
     * redirect-uri
     */
    private String redirectUri = "";

}
