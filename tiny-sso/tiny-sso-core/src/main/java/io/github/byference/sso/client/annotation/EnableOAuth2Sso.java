package io.github.byference.sso.client.annotation;

import io.github.byference.sso.client.autoconfigure.OAuth2SsoDefaultConfigurer;
import io.github.byference.sso.client.config.OAuth2SsoClientWebSecurityConfigurer;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * EnableOAuth2Sso
 *
 * @author byference
 * @since 2019/11/20
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({OAuth2SsoDefaultConfigurer.class, OAuth2SsoClientWebSecurityConfigurer.class})
public @interface EnableOAuth2Sso {
}
