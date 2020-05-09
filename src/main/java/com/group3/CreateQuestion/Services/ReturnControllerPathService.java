package com.group3.CreateQuestion.Services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReturnControllerPathService implements IReturnControllerPathService {

	private String controllerPath;
	private Logger logger = LogManager.getLogger(ReturnControllerPathService.class);

	@Override
	public String returnControllerPath(QuestionGenerationServicesEnum questionGenerationServicesEnum) {

		logger.info("REQUESTED QUESTION TYPE " + questionGenerationServicesEnum);
		switch (questionGenerationServicesEnum) {

		case FREE_TEXT:
			controllerPath = "invokeFreeText";
			break;

		case NUMERIC:
			controllerPath = "invokeNumeric";
			break;

		case MCQS_ONE:
			controllerPath = "invokeMCQSOne";
			break;

		case MCQS_MULTIPLE:
			controllerPath = "invokeMCQSMultiple";
			break;
		}
		return controllerPath;
	}
}