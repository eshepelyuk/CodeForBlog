package ua.eshepelyuk.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@SpringBootApplication
@RestController
@EnableResourceServer
public class ResourceMain extends ResourceServerConfigurerAdapter {

    @RequestMapping(method = RequestMethod.GET, path = "/user")
    public String user() {
        return "Hello User";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/admin")
    public String admin() {
        return "Hello Admin";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/anon")
    public String anon() {
        return "Hello Anon";
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/user").hasAnyRole("UUU")
            .antMatchers("/admin").hasAnyRole("AAA")
            .anyRequest().permitAll()
            .and().sessionManagement().sessionCreationPolicy(STATELESS);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer config) {
        config.tokenServices(tokenServices())
            .resourceId("qwerty")
            .stateless(true);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

    public static void main(String[] args) {
        SpringApplication.run(ResourceMain.class, args);
    }

}