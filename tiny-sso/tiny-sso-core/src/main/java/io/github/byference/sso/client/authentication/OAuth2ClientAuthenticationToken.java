package io.github.byference.sso.client.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * OAuth2ClientAuthenticationToken
 *
 * @author byference
 * @since 2019/11/20
 */
public class OAuth2ClientAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;


    private Object queryString;

    private final OAuth2AuthenticationResult authenticationResult;


    public OAuth2ClientAuthenticationToken(Object queryString, OAuth2AuthenticationResult authenticationResult) {
        super(null);
        this.queryString = queryString;
        this.authenticationResult = authenticationResult;
        setAuthenticated(false);
    }


    public OAuth2ClientAuthenticationToken(Object queryString, OAuth2AuthenticationResult authenticationResult, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.queryString = queryString;
        this.authenticationResult = authenticationResult;
        super.setAuthenticated(true); // must use super, as we override
    }

    public Object getCredentials() {
        return this.queryString;
    }

    public Object getPrincipal() {
        return this.queryString;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.queryString = null;
    }

    public OAuth2AuthenticationResult getAuthenticationResult() {
        return authenticationResult;
    }
}
