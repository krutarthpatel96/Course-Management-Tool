package com.group3.CreateQuestion.DAO;

import com.group3.CreateQuestion.BusinessModels.QuestionTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class RetrieveQuestionTypesDAOMock implements IRetrieveQuestionTypesDAO {

	ArrayList<QuestionTypes> questionTypesList;

	public static Logger logger = LogManager.getLogger(RetrieveQuestionTypesDAOMock.class);

	@Override
	public ArrayList<QuestionTypes> getQuestionTypes() {

		logger.info("inside RetrieveQuestionTypesDAO Mock function ");
		QuestionTypes questionType;
		questionTypesList = new ArrayList<>();

		questionType = new QuestionTypes();
		questionType.setQuestionType("Multiple Choice, Choose One");
		questionTypesList.add(questionType);

		questionType = new QuestionTypes();
		questionType.setQuestionType("Multiple Choice, Choose Multiple");
		questionTypesList.add(questionType);

		questionType = new QuestionTypes();
		questionType.setQuestionType("Free Text");
		questionTypesList.add(questionType);

		questionType = new QuestionTypes();
		questionType.setQuestionType("Numeric");
		questionTypesList.add(questionType);

		return questionTypesList;
	}
}