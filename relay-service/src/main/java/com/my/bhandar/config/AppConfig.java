package com.my.bhandar.config;

import io.jaegertracing.Configuration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class AppConfig {

    @Bean
    public io.opentracing.Tracer initTracer() {
        Configuration.SamplerConfiguration samplerConfig = new Configuration.SamplerConfiguration()
                .withType("const").withParam(1);
        return Configuration.fromEnv("relay-service")
                .withSampler(samplerConfig).getTracer();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }
}