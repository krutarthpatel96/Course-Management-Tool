package com.group3.CreateQuestion.Services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StartWithNumberCheckValidationService implements IStringValidatorService {

	private Logger logger = LogManager.getLogger(StartWithNumberCheckValidationService.class);

	@Override
	public boolean isValid(String inputString) {

		if (Character.isDigit(inputString.charAt(0))) {
			logger.error("digit detected at the start of the question");
			return false;
		}
		return true;
	}
}