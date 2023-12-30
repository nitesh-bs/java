package com.nitesh.springBoot3mycoolapp.injection;

import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements  Coach{
    @Override
    public String getDailyWorkout() {
        return "BaseballCoach : get Daily Workout";
    }
}
