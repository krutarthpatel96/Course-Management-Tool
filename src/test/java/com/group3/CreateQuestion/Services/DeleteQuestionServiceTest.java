package com.group3.CreateQuestion.Services;

import com.group3.CreateQuestion.DAO.DAOMockAbstractFactory;
import com.group3.CreateQuestion.DAO.IDAOAbstractFactory;
import com.group3.CreateQuestion.DAO.IRemoveQuestionDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeleteQuestionServiceTest {

	private static IDAOAbstractFactory daoInjector;
	private static IRemoveQuestionDAO removeQuestionDAO;
	public static Logger logger = LogManager.getLogger(DeleteQuestionServiceTest.class);

	public DeleteQuestionServiceTest() {

		daoInjector = DAOMockAbstractFactory.instance();
		removeQuestionDAO = daoInjector.createRemoveQuestionDAO();
	}

	@Test
	public void obtainInstructorQuestionsTest() {

		boolean result = removeQuestionDAO.removeQuestionFromDatabase("1");
		assertTrue(result);
		result = removeQuestionDAO.removeQuestionFromDatabase("5");
		assertFalse(result);
	}

}