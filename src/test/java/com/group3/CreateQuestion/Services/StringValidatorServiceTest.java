package com.group3.CreateQuestion.Services;

import com.group3.CreateQuestion.DAO.DAOMockAbstractFactory;
import com.group3.CreateQuestion.DAO.IDAOAbstractFactory;
import com.group3.CreateQuestion.DAO.IValidationRulesLoaderDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringValidatorServiceTest {

	IDAOAbstractFactory daoInjector;
	IServiceAbstractFactory serviceAbstractFactory;
	IStringValidatorService stringValidatorService;
	IValidationRulesLoaderDAO validationRulesLoaderDAO;
	static Logger logger = LogManager.getLogger(EmptyStringValidationService.class);

	public StringValidatorServiceTest() {
		daoInjector = DAOMockAbstractFactory.instance();
		serviceAbstractFactory = ServiceAbstractFactory.instance();
		validationRulesLoaderDAO = daoInjector.createValidationRulesLoaderDAO();
		stringValidatorService = serviceAbstractFactory.createStringValidatorService(validationRulesLoaderDAO);
	}

	@Test
	void isValid() {

		String emptyString = new String();
		assertFalse(stringValidatorService.isValid(emptyString));

		String specialCharcterCheck = "@# special character";
		assertTrue(stringValidatorService.isValid(specialCharcterCheck));

		String numberCheck = "1 Object Creation service ?";
		assertFalse(stringValidatorService.isValid(numberCheck));
		assertTrue(stringValidatorService.isValid("What is memento ?"));
	}
}