package io.github.byference.sso.client.autoconfigure;

import io.github.byference.sso.client.properties.OAuth2SsoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * OAuth2SsoDefaultConfiguration
 *
 * @author byfenrece
 * @since 2019/11/20
 */
@Configuration
@EnableConfigurationProperties(OAuth2SsoProperties.class)
public class OAuth2SsoDefaultConfigurer {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
