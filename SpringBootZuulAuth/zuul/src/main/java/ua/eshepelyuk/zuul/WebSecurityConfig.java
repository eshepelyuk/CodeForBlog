package ua.eshepelyuk.zuul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.web.header.HeaderWriter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@Component
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
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
