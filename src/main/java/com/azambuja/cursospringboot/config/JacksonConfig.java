package com.azambuja.cursospringboot.config;

import com.azambuja.cursospringboot.domain.BilletPayment;
import com.azambuja.cursospringboot.domain.CardPayment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(BilletPayment.class);
                objectMapper.registerSubtypes(CardPayment.class);
                super.configure(objectMapper);
            }
        };
    }
}
