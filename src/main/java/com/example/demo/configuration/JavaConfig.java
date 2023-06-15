package com.example.demo.configuration;

import com.example.demo.profiles.DevProfile;
import com.example.demo.profiles.ProductionProfile;
import com.example.demo.profiles.SystemProfile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    @ConditionalOnProperty(value = "netology.profile.dev", havingValue = "true")
    public SystemProfile devProfile(){
        return new DevProfile();
    }

    @Bean
    @ConditionalOnMissingBean(SystemProfile.class)
    public SystemProfile prodProfile(){
        return new ProductionProfile();
    }
}
