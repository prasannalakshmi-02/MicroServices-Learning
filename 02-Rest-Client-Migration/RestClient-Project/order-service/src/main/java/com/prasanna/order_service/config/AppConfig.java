package com.prasanna.order_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestClient restClient(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

        factory.setConnectTimeout(3000); // 3000ms = 3 seconds

        factory.setReadTimeout(3000);

        return RestClient.builder()
                .requestFactory(factory)
                .baseUrl("http://localhost:8081") // Bonus: Define Base URL globally here!
                .build();
    }
}
