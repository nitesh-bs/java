package com.nitesh.springBoot3mycoolapp.rest;

import com.nitesh.springBoot3mycoolapp.injection.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Value("${coach.name}")
    private String coachName;
    @Value("${team.name}")
    private String teamName;
    private Coach coach;

    //construction injection

    //    public DemoController(@Qualifier("baseballCoach") Coach coach) {
    //    if primary and qualifier both define qualifier higher priorities
    @Autowired
    public DemoController(@Qualifier("swimCoach") Coach coach) {
        this.coach = coach;
    }


//    @Autowired
//    public void setCoach(Coach coach) {
//        this.coach = coach;
//    }

    @GetMapping("/")
    public String sayHello() {
        return "sayHello!!! " + teamName + " : " + coachName + "\n Coach : " + coach.getDailyWorkout();
    }
}
