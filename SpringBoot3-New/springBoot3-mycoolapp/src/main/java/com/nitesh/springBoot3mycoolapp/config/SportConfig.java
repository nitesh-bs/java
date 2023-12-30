package com.nitesh.springBoot3mycoolapp.config;

import com.nitesh.springBoot3mycoolapp.injection.Coach;
import com.nitesh.springBoot3mycoolapp.injection.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {
    // @Bean used to make an existing third-parth class available to spring framework
    @Bean
    public Coach swimCoach(){
        return new SwimCoach();
    }

}
