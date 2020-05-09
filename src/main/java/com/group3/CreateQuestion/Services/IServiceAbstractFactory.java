package com.group3.CreateQuestion.Services;

import com.group3.CreateQuestion.DAO.IRemoveQuestionDAO;
import com.group3.CreateQuestion.DAO.IRetrieveQuestionTypesDAO;
import com.group3.CreateQuestion.DAO.IRetrieveQuestionsDAO;
import com.group3.CreateQuestion.DAO.IValidationRulesLoaderDAO;

public interface IServiceAbstractFactory {
	IObtainAllQuestionTypesService createObtainAllQuestionTypesService(
			IRetrieveQuestionTypesDAO retrieveQuestionTypesDAO);

	ICurrentTimeStampGenerationService createCurrentTimeStampGenerationService();

	IStringValidatorService createStringValidatorService(IValidationRulesLoaderDAO validationRulesLoaderDAO);

	IMakeQuestionGenerationAbstractFactory createMakeQuestionGenerationAbstractFactory();

	IReturnControllerPathService createReturnControllerPathService();

	IQuestionService createfreeTextQuestionGenerationService();

	IQuestionService createNumericQuestionGenerationService();

	IObtainQuestionsService createObtainQuestionsService(IRetrieveQuestionsDAO retrieveQuestionDAO);

	IDeleteQuestionService createDeleteQuestionService(IRemoveQuestionDAO removeQuestionDAO);

	ISplitMCQSAnswerService createSplitMCQSAnswerService();

	IQuestionService createSaveMCQAnswerstoDataBaseService();
}
