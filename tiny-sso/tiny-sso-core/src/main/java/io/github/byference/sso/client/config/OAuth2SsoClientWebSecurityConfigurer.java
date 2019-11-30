package io.github.byference.sso.client.config;

import io.github.byference.sso.client.authentication.OAuth2ClientAuthenticationProcessingFilter;
import io.github.byference.sso.client.filter.OAuth2ClientContextFilter;
import io.github.byference.sso.client.properties.OAuth2SsoProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.client.RestTemplate;

/**
 * @author byference
 * @since 2019-11-17
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2SsoClientWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final OAuth2SsoProperties oAuth2SsoProperties;

    private final RestTemplate restTemplate;

    public OAuth2SsoClientWebSecurityConfigurer(OAuth2SsoProperties oAuth2SsoProperties, RestTemplate restTemplate) {
        this.oAuth2SsoProperties = oAuth2SsoProperties;
        this.restTemplate = restTemplate;
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(oAuth2SsoProperties.getPermitUrls()).permitAll()
                .anyRequest().authenticated();

        http.addFilterAfter(new OAuth2ClientContextFilter(oAuth2SsoProperties),
                ExceptionTranslationFilter.class);

        http.apply(new SsoClientAuthenticationSecurityConfig(oAuth2SsoProperties, restTemplate));
    }


    public static class SsoClientAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

        private final OAuth2SsoProperties oAuth2SsoProperties;

        private final RestTemplate restTemplate;

        public SsoClientAuthenticationSecurityConfig(OAuth2SsoProperties oAuth2SsoProperties, RestTemplate restTemplate) {
            this.oAuth2SsoProperties = oAuth2SsoProperties;
            this.restTemplate = restTemplate;
        }

        @Override
        public void configure(HttpSecurity http) {

            OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(oAuth2SsoProperties, restTemplate);
            filter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
            filter.setSessionAuthenticationStrategy(http.getSharedObject(SessionAuthenticationStrategy.class));

            http.addFilterAfter(filter, AbstractPreAuthenticatedProcessingFilter.class);
        }
    }

}
