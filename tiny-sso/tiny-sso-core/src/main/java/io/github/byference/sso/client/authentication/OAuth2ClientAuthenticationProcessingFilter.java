package io.github.byference.sso.client.authentication;

import io.github.byference.sso.client.properties.OAuth2SsoProperties;
import io.github.byference.sso.client.properties.Oauth2ClientProperties;
import io.github.byference.sso.client.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * OAuth2ClientAuthenticationProcessingFilter
 *
 * @author byference
 * @since 2019/11/20
 */
@Slf4j
public class OAuth2ClientAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final OAuth2SsoProperties oAuth2SsoProperties;

    private final RestTemplate restTemplate;

    public OAuth2ClientAuthenticationProcessingFilter(OAuth2SsoProperties oAuth2SsoProperties, RestTemplate restTemplate) {
        super(new AntPathRequestMatcher("/login", "GET"));
        this.oAuth2SsoProperties = oAuth2SsoProperties;
        this.restTemplate = restTemplate;
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        if (!"GET".equals(request.getMethod())) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String queryString = request.getQueryString();
        log.info("GET: {} queryString is {}", request.getRequestURI(), queryString);
        Map<String, String> queryString2Map = HttpUtil.transQueryString2Map(queryString);

        if (!queryString2Map.containsKey("code")) {
            throw new InsufficientAuthenticationException("Authentication request not supported: "
                    + request.getRequestURI() + "?" + queryString);
        }

        // 利用授权码获取token
        Oauth2ClientProperties client = oAuth2SsoProperties.getClient();
        String redirectUri = client.getRedirectUri() + "?next=" + queryString2Map.get("next");
        String oauthTokenUrl = HttpUtil.getOauthTokenUrl(client.getUserAuthorizationUri(),
                client.getClientId(), client.getClientSecret(), redirectUri, queryString2Map.get("code"));

        log.info("==> send post to: {}", oauthTokenUrl);
        ResponseEntity<OAuth2AuthenticationResult> forEntity = restTemplate.postForEntity(oauthTokenUrl, null, OAuth2AuthenticationResult.class);
        // OAuth2Request
        log.info("<== get body from entity: {}", forEntity.getBody());

        OAuth2ClientAuthenticationToken authenticationResult = new OAuth2ClientAuthenticationToken("admin",
                forEntity.getBody(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        // Allow subclasses to set the "details" property
        setDetails(request, authenticationResult);
        return authenticationResult;
    }

    protected void setDetails(HttpServletRequest request, OAuth2ClientAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
