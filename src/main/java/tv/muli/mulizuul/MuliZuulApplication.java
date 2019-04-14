package tv.muli.mulizuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import tv.muli.mulizuul.filter.FeFilter;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class MuliZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuliZuulApplication.class, args);
    }

    @Bean
    public FeFilter feFilter() {
        return new FeFilter();
    }
}
