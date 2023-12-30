package com.nitesh.springBoot3mycoolapp.injection;

import org.springframework.stereotype.Component;

public class SwimCoach implements Coach{

    public SwimCoach() {
        System.out.println(
                "In constructor : "+getClass().getSimpleName()
        );
    }

    @Override
    public String getDailyWorkout() {
        return "Swim Coach : get Daily Workout";
    }
}
