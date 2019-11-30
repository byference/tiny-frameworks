package io.github.byference.sso.client.properties;

import lombok.Data;

/**
 * SsoOauth2ResourceProperties
 *
 * @author byference
 * @since 2019/11/20
 */
@Data
public class Oauth2ResourceProperties {


    private final Jwt jwt = new Jwt();


    @Data
    public class Jwt {

        /**
         * The verification key of the JWT token. Can either be a symmetric secret or
         * PEM-encoded RSA public key. If the value is not available, you can set the URI
         * instead.
         */
        private String keyValue;

        /**
         * The URI of the JWT token. Can be set if the value is not available and the key
         * is public.
         */
        private String keyUri;

    }

}
