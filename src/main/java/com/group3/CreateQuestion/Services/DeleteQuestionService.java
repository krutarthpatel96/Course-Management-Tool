package com.group3.CreateQuestion.Services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.group3.CreateQuestion.DAO.IRemoveQuestionDAO;

public class DeleteQuestionService implements IDeleteQuestionService {

	private static IRemoveQuestionDAO removeQuestionDAO;
	public static Logger logger = LogManager.getLogger(DeleteQuestionService.class);

	public DeleteQuestionService(IRemoveQuestionDAO removeQuestionDAO) {

		this.removeQuestionDAO = removeQuestionDAO;
		logger.info("DeleteQuestionService constructor called!");
	}

	@Override
	public boolean deleteQuestionByQuestionId(String questionId) {

		logger.info("Starting the process of deleting question: " + questionId + "!");
		boolean result = removeQuestionDAO.removeQuestionFromDatabase(questionId);
		logger.info("QuestionId : " + questionId + " deleted!");
		return result;
	}

}
