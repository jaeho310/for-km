package com.example.projectsample.application.model.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigContext {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
