package ua.eshepelyuk.zuul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.header.HeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@Component
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DefaultTokenServices tokenService;

    @Value("${nas.authHost}")
    private String authHost;

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/logout")
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout", HttpMethod.GET.name()))
                    .clearAuthentication(true).deleteCookies("JSESSIONID").invalidateHttpSession(true)
                    .logoutSuccessUrl(authHost + "/logout")
                    .addLogoutHandler((request, response, authentication) -> {
                        boolean res = tokenService.revokeToken(((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue());
                        LOGGER.debug("Revoke Token on logout result={}", res);
                    })
                .and()
                    .authorizeRequests()
                    .antMatchers("/profile/**").authenticated()
                    .anyRequest().permitAll()
                .and().headers().addHeaderWriter(new HeaderWriter() {
            @Override
            public void writeHeaders(HttpServletRequest request, HttpServletResponse response) {
                Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                        .flatMap($ -> $ instanceof OAuth2Authentication ? of($.getDetails()) : empty())
                        .filter($ -> $ instanceof OAuth2AuthenticationDetails)
                        .ifPresent($ -> {
                            response.setHeader("MyJWT", ((OAuth2AuthenticationDetails) $).getTokenValue());
                        });
            }
        });
    }
}
