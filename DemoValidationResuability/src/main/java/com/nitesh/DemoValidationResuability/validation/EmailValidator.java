package com.nitesh.DemoValidationResuability.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailValidator implements jakarta.validation.ConstraintValidator<ValidEmail, String> {

	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


	
	@Override
	public boolean isValid(String email, jakarta.validation.ConstraintValidatorContext context) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		if (email == null) {
			return false;
		}
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

}
