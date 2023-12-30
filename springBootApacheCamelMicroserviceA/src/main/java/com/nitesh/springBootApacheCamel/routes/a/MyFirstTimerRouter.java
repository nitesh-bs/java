package com.nitesh.springBootApacheCamel.routes.a;

import java.time.LocalDateTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class MyFirstTimerRouter extends RouteBuilder {

	@Autowired
	private GetCurrentTimeBean getCurrentTimeBean;
	
	@Autowired
	private SimpleLoggingProcessingComponent simpleLoggingProcessingComponent;
	
	@Override
	public void configure() throws Exception {
		from("timer:first-timer")
//		.log("${body}")
//		.transform().constant("My Costant Message")
//		.transform().constant("Time is : "+LocalDateTime.now())
		
		//Processing : Does not change in body of the message
		//Transformation : changes in body of the message
		
		.bean(getCurrentTimeBean)  
		.bean(simpleLoggingProcessingComponent)
		.process(new SimpleLoggingProcessor())
		.to("log:first-timer");
	}

}


@Component
class GetCurrentTimeBean{   
	public String getCurrentTime() {
		return "Time is : "+LocalDateTime.now(); 
	}
}

@Component
class SimpleLoggingProcessingComponent{
	private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);
		
	public void process(String message) {
		logger.info("SimpleLoggingProcessingComponent :: {}",message);		
	}
}

class SimpleLoggingProcessor implements Processor{

	private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessor.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {
		logger.info("SimpleLoggingProcessor :: {}",exchange);	
	}
	
}




