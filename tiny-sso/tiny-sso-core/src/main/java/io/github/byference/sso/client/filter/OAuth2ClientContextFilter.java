package io.github.byference.sso.client.filter;

import io.github.byference.sso.client.properties.OAuth2SsoProperties;
import io.github.byference.sso.client.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * MyOAuth2ClientContextFilter
 *
 * @author byference
 * @since 2019-11-23
 */
@Slf4j
public class OAuth2ClientContextFilter implements Filter {

    private final OAuth2SsoProperties oAuth2SsoProperties;

    public OAuth2ClientContextFilter(OAuth2SsoProperties oAuth2SsoProperties) {
        this.oAuth2SsoProperties = oAuth2SsoProperties;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            filterChain.doFilter(servletRequest, servletResponse);
            log.debug("Chain processed normally");
        } catch (Exception ex) {

            if (ex instanceof AccessDeniedException) {
                // 如果是 AccessDeniedException ，则重定向到sso server
                sendStartAuthentication(servletRequest, servletResponse);

            } else {
                // Rethrow ServletExceptions and RuntimeExceptions as-is
                if (ex instanceof ServletException) {
                    throw (ServletException) ex;
                } else if (ex instanceof IOException) {
                    throw (IOException) ex;
                }

                // Wrap other Exceptions. This shouldn't actually happen
                // as we've already covered all the possibilities for doFilter
                throw new RuntimeException(ex);
            }
        }
    }

    private void sendStartAuthentication(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String next = request.getRequestURI();
        if ("/error".equalsIgnoreCase(next)) {
            next = "/";
        }

        final String oauthAuthorizeUrl = HttpUtil.getOauthAuthorizeUrl(
                oAuth2SsoProperties.getClient().getUserAuthorizationUri(),
                oAuth2SsoProperties.getClient().getClientId(),
                oAuth2SsoProperties.getClient().getRedirectUri() + "?next=" + next);
        log.info("send redirect-url to: {}", oauthAuthorizeUrl);
        response.sendRedirect(oauthAuthorizeUrl);
    }

}
