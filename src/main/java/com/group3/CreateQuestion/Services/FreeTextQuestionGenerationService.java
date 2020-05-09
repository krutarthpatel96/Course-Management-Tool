package com.group3.CreateQuestion.Services;

import com.group3.CreateQuestion.DAO.ISaveBasicQuestionInformationDAO;

public class FreeTextQuestionGenerationService extends QuestionService {

	private String title, question;

	public FreeTextQuestionGenerationService() {
		super(QuestionGenerationServicesEnum.FREE_TEXT);
	}

	public String saveFreeTextQuestionGenerationServiceInfo(String title, String question, String type,
			ISaveBasicQuestionInformationDAO saveBasicQuestionInformationDAO) {

		String feedbackMessage = super.saveBasicQuestionInformation(title, question, type,
				saveBasicQuestionInformationDAO);
		return feedbackMessage;
	}
}
