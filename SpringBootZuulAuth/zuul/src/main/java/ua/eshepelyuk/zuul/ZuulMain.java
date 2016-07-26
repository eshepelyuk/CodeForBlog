package ua.eshepelyuk.zuul;

import com.netflix.zuul.ZuulFilter;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.Arrays;

@SpringBootApplication
@EnableZuulProxy
@EnableOAuth2Client
public class ZuulMain {


    @Bean
    public OAuth2ProtectedResourceDetails remote() {
        ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();

        details.setAccessTokenUri("http://localhost:8080/auth/oauth/token");
        details.setClientId("zuul");
        details.setClientSecret("zuul123");
        details.setUsername("player");
        details.setPassword("player");
        details.setScope(Arrays.asList("web"));
        return details;
    }

    @Bean(name = "qwe")
    public OAuth2RestOperations template(OAuth2ClientContext context) {
        OAuth2RestTemplate template = new OAuth2RestTemplate(remote(), context);
        template.setAccessTokenProvider(new ResourceOwnerPasswordAccessTokenProvider());
        return template;
    }

    @Bean
    public ZuulFilter filter() {
        return new ZuulFilter() {

            @Autowired
            @Qualifier("qwe")
            private OAuth2RestOperations restOperations;

            @Override
            public String filterType() {
                return "pre";
            }

            @Override
            public int filterOrder() {
                return 0;
            }

            @Override
            public boolean shouldFilter() {
                return true;
            }

            @Override
            public Object run() {
                OAuth2AccessToken token = null;
                try {
                    token = restOperations.getAccessToken();
                } catch (Exception e) {
                    LoggerFactory.getLogger(ZuulMain.class).error("OAUTH error", e);
                    e.printStackTrace();
                }
                LoggerFactory.getLogger(ZuulMain.class).info("@@@@ {}", token.getValue());
                return null;
            }
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(ZuulMain.class, args);
    }
}