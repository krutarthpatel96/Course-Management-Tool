package com.group3.CreateQuestion.Services;

import com.group3.CreateQuestion.DAO.ISaveBasicQuestionInformationDAO;

public interface IQuestionService {
	String saveBasicQuestionInformation(String questionTitle, String questionText, String questionType,
			ISaveBasicQuestionInformationDAO saveBasicQuestionInformationDAO);

	QuestionGenerationServicesEnum getQuestionType();
}
