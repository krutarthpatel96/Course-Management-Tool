package com.group3.CreateQuestion.Services;

import com.group3.CreateQuestion.DAO.IRemoveQuestionDAO;
import com.group3.CreateQuestion.DAO.IRetrieveQuestionTypesDAO;
import com.group3.CreateQuestion.DAO.IRetrieveQuestionsDAO;
import com.group3.CreateQuestion.DAO.IValidationRulesLoaderDAO;

public class ServiceAbstractFactory implements IServiceAbstractFactory {

	public static IServiceAbstractFactory serviceAbstractFactory;
	public IObtainQuestionsService obtainQuestionsService;
	public IDeleteQuestionService deleteQuestionService;
	public IObtainAllQuestionTypesService obtainAllQuestionTypesService;
	public ICurrentTimeStampGenerationService currentTimeStampGenerationService;
	public IStringValidatorService stringValidatorService;
	public IMakeQuestionGenerationAbstractFactory makeQuestionGenerationAbstractFactory;
	public IReturnControllerPathService returnControllerPathService;
	public IQuestionService questionText;
	public IQuestionService questionNumeric;
	public IQuestionService questionMCQ;
	public ISplitMCQSAnswerService splitMCQSAnswerService;

	private ServiceAbstractFactory() {
	}
	
	public static IServiceAbstractFactory instance() {

		if (null == serviceAbstractFactory) {
			serviceAbstractFactory = new ServiceAbstractFactory();
		}
		return serviceAbstractFactory;
	}

	@Override
	public IObtainAllQuestionTypesService createObtainAllQuestionTypesService(
			IRetrieveQuestionTypesDAO retrieveQuestionTypesDAO) {
		return new ObtainAllQuestionTypesService(retrieveQuestionTypesDAO);
	}

	@Override
	public ICurrentTimeStampGenerationService createCurrentTimeStampGenerationService() {
		return new CurrentTimeStampGenerationService();
	}

	@Override
	public IStringValidatorService createStringValidatorService(IValidationRulesLoaderDAO validationRulesLoaderDAO) {
		return new StringValidatorService(validationRulesLoaderDAO);
	}

	@Override
	public IMakeQuestionGenerationAbstractFactory createMakeQuestionGenerationAbstractFactory() {
		return new MakeQuestionGenerationAbstractFactory();
	}

	@Override
	public IReturnControllerPathService createReturnControllerPathService() {
		return new ReturnControllerPathService();
	}

	@Override
	public IQuestionService createfreeTextQuestionGenerationService() {
		return new FreeTextQuestionGenerationService();
	}

	@Override
	public IQuestionService createNumericQuestionGenerationService() {

		return new NumericQuestionGenerationService();
	}

	@Override
	public IQuestionService createSaveMCQAnswerstoDataBaseService() {
		return new SaveMCQAnswerstoDataBaseService();
	}

	@Override
	public IObtainQuestionsService createObtainQuestionsService(IRetrieveQuestionsDAO retrieveQuestionDAO) {
		return new ObtainQuestionsService(retrieveQuestionDAO);
	}

	@Override
	public IDeleteQuestionService createDeleteQuestionService(IRemoveQuestionDAO removeQuestionDAO) {
		return new DeleteQuestionService(removeQuestionDAO);
	}

	@Override
	public ISplitMCQSAnswerService createSplitMCQSAnswerService() {
		return new SplitMCQSAnswerService();
	}
}