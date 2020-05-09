package com.group3.CreateQuestion.DAO;

import com.group3.DBConnectivity.ObtainDataBaseConnection;
import com.group3.CreateQuestion.BusinessModels.MCQAnswers;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SaveMCQAnswerstoDataBaseDAO implements ISaveMCQAnswerstoDataBaseDAO {
	private Connection connection;
	private String query;
	private PreparedStatement statement;
	private int resultStatus = 0;
	private Logger logger = LogManager.getLogger(SaveMCQAnswerstoDataBaseDAO.class);

	@Override
	public int saveOptionsToDataBase(int id, ArrayList<MCQAnswers> mcqAnswers) {
		query = "INSERT INTO MULTIPLE_CHOICE_QUESTIONS(QUESTION_ID,OPTION,STOREDAS) VALUES(?,?,?)";
		connection = ObtainDataBaseConnection.obtainDatabaseConnection();
		int[] rowsUpdatedCounter;

		try {
			statement = connection.prepareStatement(query);
			for (MCQAnswers mcqAnsersInstance : mcqAnswers) {
				statement.setInt(1, id);
				statement.setString(2, mcqAnsersInstance.getAnswer());
				statement.setString(3, mcqAnsersInstance.getStoredAs());
				statement.addBatch();
			}

			rowsUpdatedCounter = statement.executeBatch();
			logger.log(Level.INFO, " no of rows Appended : " + rowsUpdatedCounter.length);
			if (rowsUpdatedCounter.length == mcqAnswers.size()) {
				resultStatus = 1;
			}
		} catch (SQLException e) {
			logger.error("Failure reasons" + e.getSQLState());
			logger.error("failed to apped row due to database connectivity problem " + e.getMessage());
		}
		return resultStatus;
	}
}
