package com.registration.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Springconfig {

    @Bean
    public ModelMapper getModelmapper(){
        return new ModelMapper();
    }

    
}
