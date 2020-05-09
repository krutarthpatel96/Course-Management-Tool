package com.group3.CreateQuestion.Services;

import com.group3.CreateQuestion.DAO.DAOMockAbstractFactory;
import com.group3.CreateQuestion.DAO.IDAOAbstractFactory;
import com.group3.CreateQuestion.DAO.ISaveBasicQuestionInformationDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class freeTextQuestionGenerationServiceTest {

	IDAOAbstractFactory daoInjector;
	IServiceAbstractFactory serviceAbstractFactory;
	IQuestionService questionService;
	ISaveBasicQuestionInformationDAO saveBasicQuestionInformationDAO;
	Logger logger = LogManager.getLogger(QuestionServiceTest.class);

	public freeTextQuestionGenerationServiceTest() {

		daoInjector = DAOMockAbstractFactory.instance();
		serviceAbstractFactory = ServiceAbstractFactory.instance();
		questionService = serviceAbstractFactory.createfreeTextQuestionGenerationService();
		saveBasicQuestionInformationDAO = daoInjector.createSaveBasicQuestionInformationDAO(
				serviceAbstractFactory.createCurrentTimeStampGenerationService());
	}

	@Test
	void saveFreeTextQuestionGenerationServiceInfo() {

		String id = questionService.saveBasicQuestionInformation("Design Pattern", "What is memento pattern?",
				"Free text", saveBasicQuestionInformationDAO);
		logger.info("id generated from mock object" + id);
		assertNotNull(id);
	}
}