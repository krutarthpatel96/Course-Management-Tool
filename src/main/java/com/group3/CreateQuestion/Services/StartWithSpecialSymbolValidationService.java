package com.group3.CreateQuestion.Services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

public class StartWithSpecialSymbolValidationService implements IStringValidatorService {

	private String startWithSpecialCharRegex = "[!@#$%^&*(),.?\":{}|<>][A-Za-z|0-9]+";
	private Logger logger = LogManager.getLogger(StartWithNumberCheckValidationService.class);

	@Override
	public boolean isValid(String inputString) {

		if (Pattern.matches(startWithSpecialCharRegex, inputString)) {
			logger.error("special character detected at the start of the question");
			return false;
		}
		return true;
	}
}