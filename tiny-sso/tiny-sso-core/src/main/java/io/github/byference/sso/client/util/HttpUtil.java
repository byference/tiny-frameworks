package io.github.byference.sso.client.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * HttpUtil
 *
 * @author byference
 * @since 2019/11/19
 */
@Slf4j
public class HttpUtil {


    private HttpUtil() {}


    /**
     * get OauthAuthorizeUrl
     */
    public static String getOauthTokenUrl(String userAuthorizationUri, String clientId, String clientSecret, String redirectUri, String code) {

        String oauthTokenUrlFormat = "%s?client_id=%s&client_secret=%s&grant_type=authorization_code&redirect_uri=%s&code=%s";
        return String.format(oauthTokenUrlFormat, userAuthorizationUri, clientId, clientSecret, redirectUri, code);
    }


    /**
     * get OauthAuthorizeUrl
     */
    public static String getOauthAuthorizeUrl(String userAuthorizationUri, String clientId, String redirectUri) {

        String oauthAuthorizeUrlFormat = "%s?client_id=%s&response_type=code&redirect_uri=%s";
        return String.format(oauthAuthorizeUrlFormat, userAuthorizationUri, clientId, redirectUri);
    }


    /**
     * trans queryString to map
     */
    public static Map<String, String> transQueryString2Map(String queryString) {

        Map<String, String> result = new HashMap<>();
        if (StringUtils.isEmpty(queryString)) {
            return result;
        }

        String[] splits = StringUtils.split(queryString, "&");
        if (splits != null) {
            for (String split : splits) {
                String[] arr = split.split("=");
                if (arr.length == 2) {
                    result.put(arr[0], arr[1]);
                }
            }
        }
        return result;
    }

}
