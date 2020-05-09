package com.group3.CreateQuestion.DAO;

import com.group3.CreateQuestion.Services.ICurrentTimeStampGenerationService;

public interface IDAOAbstractFactory {
	IRetrieveQuestionTypesDAO createRetrieveQuestionTypesDAO();

	ISaveBasicQuestionInformationDAO createSaveBasicQuestionInformationDAO(
			ICurrentTimeStampGenerationService currentTimeStampGenerationService);

	IValidationRulesLoaderDAO createValidationRulesLoaderDAO();

	IRetrieveQuestionsDAO createRetrieveQuestionsDAO();

	IRemoveQuestionDAO createRemoveQuestionDAO();

	ISaveMCQAnswerstoDataBaseDAO createSaveMCQAnswertoDataBaseDAO();
}
