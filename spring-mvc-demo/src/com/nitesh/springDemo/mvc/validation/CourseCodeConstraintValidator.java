package com.nitesh.springDemo.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

	private String[] coursePrefix;
	
	@Override
	public void initialize(CourseCode courseCode) {
		coursePrefix = courseCode.value();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext arg1) {
		
		boolean result = false;
		if(value != null )
			for(String tempPrefix : coursePrefix) {
				result=value.startsWith(tempPrefix) ;
				if(result)
					break;
			}
			 
		else
			result=true;
		
		return result;
	}

}
