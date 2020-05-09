package com.group3.CreateQuestion.Services;

import com.group3.CreateQuestion.DAO.ISaveBasicQuestionInformationDAO;

public class NumericQuestionGenerationService extends QuestionService {

	public NumericQuestionGenerationService() {

		super(QuestionGenerationServicesEnum.NUMERIC);
	}

	public String saveNumericQuestion(String title, String question, String type,
			ISaveBasicQuestionInformationDAO saveBasicQuestionInformationDAO) {

		String feedbackMessage = super.saveBasicQuestionInformation(title, question, type,
				saveBasicQuestionInformationDAO);
		return feedbackMessage;
	}
}