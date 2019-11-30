package com.github.byference;

import io.github.byference.sso.client.annotation.EnableOAuth2Sso;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author byference
 * @since 2019-11-17
 **/
@EnableOAuth2Sso
@SpringBootApplication
public class SsoClient2Application {

    public static void main(String[] args) {
        SpringApplication.run(SsoClient2Application.class, args);
    }
}
