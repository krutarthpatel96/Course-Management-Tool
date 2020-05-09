package com.group3.AdminAndAuthorization.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.group3.DBConnectivity.ObtainDataBaseConnection;

public class InstructorHandlerDAO implements IInstructorHandlerDAO {
	private Connection connection;
	PreparedStatement statement;
	String query;
	private ArrayList<String> courseList;

	private static Logger logger = LogManager.getLogger(InstructorHandlerDAO.class);

	public InstructorHandlerDAO() {
		connection = ObtainDataBaseConnection.obtainDatabaseConnection();
		courseList = new ArrayList<>();
	}

	@Override
	public String createNewInstructor(String MailId, String CourseId) {
		String feedbackMessage = new String();
		int queryResult;
		query = "INSERT INTO ALLOCATE_INSTRUCTOR(MAIL_ID,COURSE_ID) VALUES(?,?)";

		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, MailId);
			statement.setString(2, CourseId);
			queryResult = statement.executeUpdate();

			if (queryResult > 0) {
				feedbackMessage = "Instructor Assigned for " + CourseId;
			}
		}

		catch (SQLException e) {
			logger.error(e);
		}

		return feedbackMessage;
	}

	@Override
	public boolean isInstructorExists(String MailId) {
		int queryResult;
		boolean state;
		query = "select *from ALLOCATE_INSTRUCTOR where MAIL_ID = ?";

		try {
			statement = ObtainDataBaseConnection.obtainDatabaseConnection().prepareStatement(query);
			statement.setString(1, MailId);
			state = statement.execute();

			return state;
		}

		catch (SQLException e) {
			logger.error(e);
		}

		return false;
	}

	@Override
	public String deleteinstructor(String MailId) {
		int queryResult;
		String feedBackMessage = new String();
		String query = "DELETE FROM ALLOCATE_INSTRUCTOR WHERE MAIL_ID = ?";

		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, MailId);
			queryResult = statement.executeUpdate();

			if (queryResult > 0) {
				feedBackMessage = "Instructor deleted";
			}
		}

		catch (SQLException e) {
			logger.error(e);
		}

		return feedBackMessage;
	}

	@Override
	public ArrayList<String> getInstructorCourses(String MailId) {
		ResultSet result;
		query = "select *from ALLOCATE_INSTRUCTOR where MAIL_ID = ?";

		try {
			statement = ObtainDataBaseConnection.obtainDatabaseConnection().prepareStatement(query);
			statement.setString(1, MailId);
			result = statement.executeQuery();

			while (result.next()) {
				courseList.add(result.getString("COURSE_ID"));
			}
		}

		catch (SQLException e) {
			logger.error(e);
		}

		return courseList;
	}
}