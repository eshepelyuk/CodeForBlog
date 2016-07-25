package ua.eshepelyuk.zuul;

import com.netflix.zuul.ZuulFilter;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class ZuulMain {




    @Bean
    public ZuulFilter filter() {
        return new ZuulFilter() {


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
                LoggerFactory.getLogger(ZuulMain.class).info("@@@@@@@@@@@@@@@@");
                return null;
            }
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(ZuulMain.class, args);
    }
}