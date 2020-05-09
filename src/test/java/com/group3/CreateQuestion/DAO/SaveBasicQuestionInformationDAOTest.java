package com.group3.CreateQuestion.DAO;

import com.group3.DBConnectivity.ObtainDataBaseConnection;
import com.group3.CreateQuestion.Services.ICurrentTimeStampGenerationService;
import com.group3.CreateQuestion.Services.IServiceAbstractFactory;
import com.group3.CreateQuestion.Services.ServiceAbstractFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SaveBasicQuestionInformationDAOTest {

	ISaveBasicQuestionInformationDAO saveBasicQuestionInformationDAO;
	String returnMessageFromDataBase, queryString;
	Connection connection;

	private static Logger logger = LogManager.getLogger(SaveBasicQuestionInformationDAOTest.class);

	@BeforeEach
	void setUp() {

		IDAOAbstractFactory idaoInjector = DAOAbstractFactory.instance();
		IServiceAbstractFactory serviceAbstractFactory;
		ICurrentTimeStampGenerationService currentTimeStampGenerationService;

		returnMessageFromDataBase = null;
		serviceAbstractFactory = ServiceAbstractFactory.instance();
		currentTimeStampGenerationService = serviceAbstractFactory.createCurrentTimeStampGenerationService();
		saveBasicQuestionInformationDAO = idaoInjector
				.createSaveBasicQuestionInformationDAO(currentTimeStampGenerationService);

	}

	@AfterEach
	void tearDown() {

		PreparedStatement statement;
		queryString = "DELETE FROM QUESTION WHERE QUESTION_ID=?";
		if (returnMessageFromDataBase != null) {
			try {
				connection = ObtainDataBaseConnection.obtainDatabaseConnection();
				statement = connection.prepareStatement(queryString);
				statement.setString(1, returnMessageFromDataBase);
			} catch (SQLException e) {
				logger.error("Server connectivity problem ! (Check Internet connectivity)");
			}
		}
	}

	@Test
	void saveDetails() {

		returnMessageFromDataBase = saveBasicQuestionInformationDAO.saveDetailsAndReturnId("Programming Capacity",
				"How many hours you can spent on programming per week ?", "Free text");
		assertNotNull(returnMessageFromDataBase);
	}
}