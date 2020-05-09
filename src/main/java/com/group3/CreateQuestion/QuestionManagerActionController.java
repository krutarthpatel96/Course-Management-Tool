package com.group3.CreateQuestion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuestionManagerActionController {

	private Logger logger = LogManager.getLogger(QuestionManagerActionController.class);

	@RequestMapping("/QuestionManager")
	public String renderQuestionGenerationPage() {

		logger.info("QuestionManager Request Triggered!");
		return "QuestionManager.html";
	}

}
