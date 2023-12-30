package com.nitesh.annotationDemo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TennisCoach implements Coach , DisposableBean{

	//Field Injection
	@Autowired
	@Qualifier("randomFortuneService")
	private FortuneService fortuneService;

	// Constructor Annotation
	/*
	 * @Autowired public TennisCoach(FortuneService fortuneService) { this.fortuneService = fortuneService; }
	 */

	
	
	@Autowired
	public TennisCoach() {
		System.out.println("Constructor : TennisCoach()");
	}
	
	@PostConstruct
	public void getPostContruct() {
		System.out.println("Tennis Coach : getPostConstruct()");
	}
	
	@PreDestroy
	private void getPreDestroy() {
		// TODO Auto-generated method stub
		System.out.println("Tennis Coach : getPreDestroy()");
	}

	/*
	 * public TennisCoach(FortuneService fortuneService) { this.fortuneService =
	 * fortuneService; }
	 */
	
	public FortuneService getFortuneService() {
		return fortuneService;
	}

	// Setter Injection
	/*
	 * @Autowired
	 * @Qualifier("happyFortuneService")
	 *  public void setFortuneService(FortuneService fortuneService) {
	 * System.out.println("tennisCoach : goAnnotationFortuneService()");
	 * this.fortuneService = fortuneService; }
	 */

	// Method Injection
	/*
	 * @Autowired
	 * @Qualifier("happyFortuneService")
	 *  public void goAnnotationFortuneService(FortuneService
	 * fortuneService) {
	 * System.out.println("tennisCoach : goAnnotationFortuneService()");
	 * this.fortuneService = fortuneService; }
	 */

	@Override
	public String getDailyWorkout() {
		// TODO Auto-generated method stub
		return "Tennis Coach : getDailyWorkout()";
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return "Tennis Coach : getDailyFortune() : " + fortuneService.getFortune();
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(">> TennisCoach: inside destroy()");
	}

}
