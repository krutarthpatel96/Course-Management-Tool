package com.group3.CreateQuestion.Services;

import com.group3.CreateQuestion.DAO.DAOMockAbstractFactory;
import com.group3.CreateQuestion.DAO.IDAOAbstractFactory;
import com.group3.CreateQuestion.DAO.ISaveBasicQuestionInformationDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionServiceTest {

	IDAOAbstractFactory daoInjector;
	IServiceAbstractFactory serviceAbstractFactory;
	IQuestionService questionService;
	ISaveBasicQuestionInformationDAO saveBasicQuestionInformationDAO;
	Logger logger = LogManager.getLogger(QuestionServiceTest.class);

	@BeforeEach
	void setUp() {

		daoInjector = DAOMockAbstractFactory.instance();
		serviceAbstractFactory = ServiceAbstractFactory.instance();
		questionService = serviceAbstractFactory.createfreeTextQuestionGenerationService();
		saveBasicQuestionInformationDAO = daoInjector.createSaveBasicQuestionInformationDAO(
				serviceAbstractFactory.createCurrentTimeStampGenerationService());
	}

	@Test
	void saveBasicQuestionInformation() {

		String id = questionService.saveBasicQuestionInformation("Design Pattern", "What is memento pattern?",
				"Free text", saveBasicQuestionInformationDAO);
		logger.info("id generated from mock" + id);
		assertNotNull(id);
	}

	@Test
	void getQuestionType() {

		QuestionGenerationServicesEnum questionGenerationServicesEnum = QuestionGenerationServicesEnum.FREE_TEXT;
		assertNotEquals(questionService.getQuestionType(), QuestionGenerationServicesEnum.MCQS_MULTIPLE);
		assertEquals(questionGenerationServicesEnum, questionService.getQuestionType());
	}
}