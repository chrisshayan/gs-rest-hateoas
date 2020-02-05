package com.chrisshayan.sample.hateoas;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.server.core.EvoInflectorLinkRelationProvider;

@SpringBootApplication
public class SpringHateoasSimplifiedApplication {
    public static void main(String... args) {
        SpringApplication.run(SpringHateoasSimplifiedApplication.class);
    }


    @Bean
    EvoInflectorLinkRelationProvider relProvider() {
        return new EvoInflectorLinkRelationProvider();
    }
}
