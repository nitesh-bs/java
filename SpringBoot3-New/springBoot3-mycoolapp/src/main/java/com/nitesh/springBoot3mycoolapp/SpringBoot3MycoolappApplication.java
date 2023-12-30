package com.nitesh.springBoot3mycoolapp;

import com.nitesh.springBoot3mycoolapp.injection.Coach;
import com.nitesh.springBoot3mycoolapp.injection.SwimCoach;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringBoot3MycoolappApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3MycoolappApplication.class, args);
    }


}
