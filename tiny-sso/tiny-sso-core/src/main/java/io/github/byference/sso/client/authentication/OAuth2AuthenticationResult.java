package io.github.byference.sso.client.authentication;

import lombok.Data;

/**
 * OAuth2AuthenticationResult
 *
 * @author byference
 * @since 2019/11/21
 */
@Data
public class OAuth2AuthenticationResult {

    /**
     * access_token
     */
    private String access_token;

    /**
     * token_type
     */
    private String token_type;

    /**
     * refresh_token
     */
    private String refresh_token;

    /**
     * expires_in
     */
    private String expires_in;

    /**
     * scope
     */
    private String scope;
}
