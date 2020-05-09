package com.group3.CreateQuestion.DAO;

import com.group3.CreateQuestion.Services.ICurrentTimeStampGenerationService;

public class DAOMockAbstractFactory implements IDAOAbstractFactory {

	public static IDAOAbstractFactory idaoInjector;
	public static IRetrieveQuestionTypesDAO retrieveQuestionTypesDAO;
	public static ISaveBasicQuestionInformationDAO saveBasicQuestionInformationDAO;
	public static IValidationRulesLoaderDAO validationRulesLoaderDAO;
	public static IRetrieveQuestionsDAO retrieveQuestionsDAO;
	public static IRemoveQuestionDAO removeQuestionDAO;

	private DAOMockAbstractFactory() {
	}

	public static IDAOAbstractFactory instance() {
		if (null == idaoInjector) {
			idaoInjector = new DAOMockAbstractFactory();
		}
		return idaoInjector;
	}

	@Override
	public IRetrieveQuestionTypesDAO createRetrieveQuestionTypesDAO() {
		return new RetrieveQuestionTypesDAOMock();
	}

	@Override
	public ISaveBasicQuestionInformationDAO createSaveBasicQuestionInformationDAO(
			ICurrentTimeStampGenerationService currentTimeStampGenerationService) {
		return new SaveBasicQuestionInformationDAOMock(currentTimeStampGenerationService);
	}

	@Override
	public IValidationRulesLoaderDAO createValidationRulesLoaderDAO() {
		return new ValidationRulesDAOLoaderMock();
	}

	@Override
	public IRetrieveQuestionsDAO createRetrieveQuestionsDAO() {
		return new RetrieveQuestionsDAOMock();
	}

	@Override
	public IRemoveQuestionDAO createRemoveQuestionDAO() {
		return new RemoveQuestionDAOMock();
	}

	@Override
	public ISaveMCQAnswerstoDataBaseDAO createSaveMCQAnswertoDataBaseDAO() {
		return new SaveMCQAnswerstoDataBaseDAOMock();
	}
}