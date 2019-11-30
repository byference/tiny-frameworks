package io.github.byference.sso.client.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * OAuth2SsoProperties
 *
 * @author byference
 * @since 2019/11/19
 */
@Data
@ConfigurationProperties("security.oauth2")
public class OAuth2SsoProperties {

    /**
     * permit-urls
     */
    private String[] permitUrls = {};


    @NestedConfigurationProperty
    private final Oauth2ClientProperties client = new Oauth2ClientProperties();


    @NestedConfigurationProperty
    private final Oauth2ResourceProperties resource = new Oauth2ResourceProperties();

}
