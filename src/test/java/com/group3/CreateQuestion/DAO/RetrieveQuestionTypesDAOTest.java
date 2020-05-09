package com.group3.CreateQuestion.DAO;

import com.group3.DBConnectivity.ObtainDataBaseConnection;
import com.group3.CreateQuestion.BusinessModels.QuestionTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RetrieveQuestionTypesDAOTest {

	IDAOAbstractFactory idaoInjector;
	IRetrieveQuestionTypesDAO retrieveQuestionTypesDAO;
	ArrayList<QuestionTypes> possibleQuestionType;

	Connection connection;
	PreparedStatement statement;
	String queryString, testDatabaseEntry;

	public static Logger logger = LogManager.getLogger(RetrieveQuestionTypesDAOTest.class);;

	@BeforeEach
	void setUp() {

		idaoInjector = DAOAbstractFactory.instance();
		retrieveQuestionTypesDAO = idaoInjector.createRetrieveQuestionTypesDAO();
		connection = ObtainDataBaseConnection.obtainDatabaseConnection();
		queryString = "INSERT INTO QUESTION_TYPE(TYPES) VALUES(?)";
		testDatabaseEntry = "EntryQuestionTypeCheck";

		try {
			statement = connection.prepareStatement(queryString);
			statement.setString(1, testDatabaseEntry);

		} catch (SQLException e) {
			logger.error("Server connectivity problem! (Check Internet connectivity)");
		}
	}

	@AfterEach
	void tearDown() {

		queryString = "DELETE FROM QUESTION_TYPE WHERE TYPES=?";
		try {
			statement = connection.prepareStatement(queryString);
			statement.setString(1, testDatabaseEntry);
		} catch (SQLException e) {
			logger.error("Server connectivity problem! (Check Internet connectivity)");
		}
	}

	@Test
	void getQuestionTypes() {

		possibleQuestionType = this.retrieveQuestionTypesDAO.getQuestionTypes();
		assertNotNull(possibleQuestionType.size() > 0);
	}
}