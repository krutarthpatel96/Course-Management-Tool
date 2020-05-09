package com.group3.CreateQuestion.DAO;

import com.group3.DBConnectivity.ObtainDataBaseConnection;
import com.group3.CreateQuestion.BusinessModels.QuestionTypes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RetrieveQuestionTypesDAO implements IRetrieveQuestionTypesDAO {
	private ArrayList<QuestionTypes> questionType;
	private String query, questionTypeText;
	private Connection connection;
	private PreparedStatement statement;
	private Logger logger = LogManager.getLogger(RetrieveQuestionTypesDAO.class);
	private QuestionTypes questTypeInstance;

	@Override
	public ArrayList<QuestionTypes> getQuestionTypes() {

		ResultSet resultSet;
		questionType = new ArrayList<>();
		query = "select TYPES from QUESTION_TYPE";

		try {
			connection = ObtainDataBaseConnection.obtainDatabaseConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				logger.info(resultSet.getObject("TYPES"));
				questionTypeText = resultSet.getObject("TYPES").toString();
				questTypeInstance = new QuestionTypes();
				questTypeInstance.setQuestionType(questionTypeText);
				questionType.add(questTypeInstance);
			}
		} catch (SQLException e) {
			logger.error("facing database server connectivity error");
		}
		return questionType;
	}
}