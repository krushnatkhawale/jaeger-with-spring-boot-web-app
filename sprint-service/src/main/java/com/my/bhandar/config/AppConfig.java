package com.my.bhandar.config;

import io.jaegertracing.Configuration;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class AppConfig {

    @Bean
    public io.opentracing.Tracer initTracer() {
        Configuration.SamplerConfiguration samplerConfig = new Configuration.SamplerConfiguration().withType("const").withParam(1);
        return Configuration.fromEnv("sprint-service").withSampler(samplerConfig).getTracer();
    }
}