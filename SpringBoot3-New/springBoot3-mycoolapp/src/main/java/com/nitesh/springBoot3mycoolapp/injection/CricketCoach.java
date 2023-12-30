package com.nitesh.springBoot3mycoolapp.injection;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
//marks the class as spring bean and available for dependency injection.
@Component
//@Primary
//@Lazy
//spring.main.lazy-initialization=true for golable Lazy init
// if primary and qualifier both define qualifier higher priorities
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CricketCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "CricketCoach DailyWorkout";
    }

    @PostConstruct
    public void doMyPostConstruct(){
        System.out.println("Cricket Coach Post Construct");
    }

    @PreDestroy
    public void doMyPreDestroy()
    {
        System.out.println("Cricket Coach Pre Destory");
    }
}
