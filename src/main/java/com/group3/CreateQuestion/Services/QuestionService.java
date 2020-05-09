package com.group3.CreateQuestion.Services;

import com.group3.CreateQuestion.BusinessModels.Question;
import com.group3.CreateQuestion.DAO.ISaveBasicQuestionInformationDAO;

public abstract class QuestionService implements IQuestionService {
	private QuestionGenerationServicesEnum questionGenerationServicesEnum;

	public QuestionService(QuestionGenerationServicesEnum questionGenerationServicesEnum) {
		this.questionGenerationServicesEnum = questionGenerationServicesEnum;
	}

	@Override
	public String saveBasicQuestionInformation(String questionTitle, String questionText, String questionType,
			ISaveBasicQuestionInformationDAO saveBasicQuestionInformationDAO) {

		String feedbackMessage = Question.saveBasicInformationIntoDatabase(saveBasicQuestionInformationDAO,
				questionTitle, questionText, questionType);
		return feedbackMessage;
	}

	@Override
	public QuestionGenerationServicesEnum getQuestionType() {
		return questionGenerationServicesEnum;
	}
}