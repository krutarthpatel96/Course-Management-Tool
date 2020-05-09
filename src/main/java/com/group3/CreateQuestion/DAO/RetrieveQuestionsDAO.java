package com.group3.CreateQuestion.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.group3.DBConnectivity.ObtainDataBaseConnection;

public class RetrieveQuestionsDAO implements IRetrieveQuestionsDAO {
	Connection connection;
	String query;
	PreparedStatement statement;
	List<List<String>> instructorQuestions;
	List<String> questionInfo;

	private Logger logger = LogManager.getLogger(RetrieveQuestionsDAO.class);

	@Override
	public List<List<String>> getQuestionsByInstructorID(String instructorId, String order) {

		instructorQuestions = new ArrayList<List<String>>();
		ResultSet result;
		String id;
		String title;
		String text;
		String type;
		String timestamp;

		try {
			connection = ObtainDataBaseConnection.obtainDatabaseConnection();
			logger.info("Fetching questions for instructor: " + instructorId);
			query = "select * from QUESTIONS Q " + "JOIN INSTRUCTOR_QUESTION_MAPPING I " + "WHERE I.INSTRUCTOR_ID = '"
					+ instructorId + "' AND I.QUESTION_ID = Q.QUESTION_ID " + order + ";";
			statement = connection.prepareStatement(query);

			result = statement.executeQuery();

			while (result.next()) {

				id = String.valueOf(result.getObject("QUESTION_ID"));
				title = String.valueOf(result.getObject("TITLE"));
				text = String.valueOf(result.getObject("TEXT"));
				type = String.valueOf(result.getObject("TYPE"));
				timestamp = String.valueOf(result.getObject("TIMESTAMP"));

				logger.info("Question fetched: " + id);

				questionInfo = new ArrayList<String>();
				questionInfo.add(id);
				questionInfo.add(title);
				questionInfo.add(text);
				questionInfo.add(type);
				questionInfo.add(timestamp);

				instructorQuestions.add(questionInfo);
			}

			connection.close();
			logger.info("Questions fetched for the instructor with ID: " + instructorId);
		} catch (Exception e) {
			logger.error("Exception at RetrieveQuestionsDAO while fetching instructor questions! " + e);
		}
		return instructorQuestions;
	}
}