package com.group3.CreateQuestion.DAO;

import com.group3.DBConnectivity.ObtainDataBaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RetrieveQuestionsDAOTest {

	private static IDAOAbstractFactory daoInjector;
	private static IRetrieveQuestionsDAO retrieveQuestionsDAO;
	private static Logger logger = LogManager.getLogger(RetrieveQuestionsDAOTest.class);
	public static List<List<String>> questionList;

	public static Connection conn;
	public static String sql;
	public static PreparedStatement statement;

	public static int questionId;
	public static int result;

	@BeforeAll
	public static void setUp() throws SQLException {
		daoInjector = DAOAbstractFactory.instance();
		retrieveQuestionsDAO = daoInjector.createRetrieveQuestionsDAO();
		logger.info("ObtainQuestionsServiceTest setup!");
	}

	@BeforeEach
	public void init() throws SQLException {

		ResultSet resultData;

		conn = ObtainDataBaseConnection.obtainDatabaseConnection();
		sql = "insert into QUESTIONS values (0,'Test Title 2','Test Text 2','Test Type 2',NULL)";
		statement = conn.prepareStatement(sql);
		result = statement.executeUpdate();
		logger.info("Test Question Inserted!");

		sql = "select MAX(QUESTION_ID) as QUESTION_ID from QUESTIONS;";
		statement = conn.prepareStatement(sql);

		resultData = statement.executeQuery();
		while (resultData.next()) {
			questionId = resultData.getInt("QUESTION_ID");
		}
		logger.info("Test QuestionId retrieved for testing purpose!");

		sql = "insert into INSTRUCTOR_QUESTION_MAPPING values ('test@dal.ca'," + questionId + ");";
		statement = conn.prepareStatement(sql);
		result = statement.executeUpdate();
		logger.info("Test Instructor Inserted!");

		conn.close();
	}

	@AfterEach
	public void tearDown() throws SQLException {

		conn = ObtainDataBaseConnection.obtainDatabaseConnection();
		sql = "delete from QUESTIONS where TITLE IN ('Test Title 2');";
		statement = conn.prepareStatement(sql);
		result = statement.executeUpdate();
		System.out.println("Test Question Deleted!");

		sql = "delete from INSTRUCTOR_QUESTION_MAPPING where INSTRUCTOR_ID IN ('test@dal.ca');";
		statement = conn.prepareStatement(sql);
		result = statement.executeUpdate();
		System.out.println("Test Instructor Deleted!");

		conn.close();
	}

	@Test
	public void obtainInstructorQuestionsTest() {

		questionList = retrieveQuestionsDAO.getQuestionsByInstructorID("test@dal.ca", "");
		assertTrue(questionList.size() > 0);
		assertTrue(questionList.size() == 1);

	}
}