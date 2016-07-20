package ua.eshepelyuk.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootApplication
@EnableZuulProxy
@RestController
public class ZuulMain {

    @RequestMapping(method = RequestMethod.GET, path = "/loggout")
    public String loggout() {
        return  "<html><body>" +
            "<form action=/logout method=POST><input type=submit value=logoutHere></form>" +
            "<form action=http://localhost:8080/auth/logout method=POST><input type=submit value=logoutThere></form>" +
            "<body></html>";
    }

    @Bean
    public Filter corsFilter() {
        return new Filter() {
            @Override
            public void init(FilterConfig filterConfig) throws ServletException {
            }

            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Credentials", "true");
                response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS, GET, DELETE");
                response.setHeader("Access-Control-Max-Age", "3600");
                response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Origin, Content-Type, Authorization, Accept");
                filterChain.doFilter(servletRequest, servletResponse);
            }

            @Override
            public void destroy() {
            }
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(ZuulMain.class, args);
    }

}