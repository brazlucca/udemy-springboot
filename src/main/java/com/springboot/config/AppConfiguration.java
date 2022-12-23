package com.springboot.config;

import com.springboot.annotation.Development;
import com.springboot.annotation.Production;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AppConfiguration {

    @Bean
    @Development
    public CommandLineRunner executarDevelopment(){
        return args -> {
            System.out.println("RODANDO A CONFIGURACA DE DESENVOLVIMENTO");
        };
    }

    @Bean
    @Production
    public CommandLineRunner executarProduction(){
        return args -> {
            System.out.println("RODANDO A CONFIGURACA DE PRODUCTION");
        };
    }

}
