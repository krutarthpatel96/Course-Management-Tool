package com.group3.CreateQuestion.Services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmptyStringValidationService implements IStringValidatorService {

	private Logger logger = LogManager.getLogger(EmptyStringValidationService.class);

	@Override
	public boolean isValid(String inputString) {

		if (inputString.isEmpty()) {
			logger.error("Empty String detected");
			return false;
		}
		return true;
	}
}