package com.group3.Course.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.group3.BusinessModels.Student;
import com.group3.DBConnectivity.ObtainDataBaseConnection;
import com.group3.Course.CourseController;

public class StudentDAO implements IStudentDAO {
	Connection connection;
	String query;
	private static Logger logger = LogManager.getLogger(StudentDAO.class);
	PreparedStatement statement;
	Student studentDetails;

	@Override
	public ArrayList<Student> getAllStudents() {

		ArrayList<Student> rows = new ArrayList<Student>();
		try {
			connection = ObtainDataBaseConnection.obtainDatabaseConnection();
			query = "SELECT * FROM USER_DATABASE where ROLE = 'Student'";
			statement = connection.prepareStatement(query);

			ResultSet result = statement.executeQuery();
			logger.info("QUERY EXECUTED");
			while (result.next()) {
				logger.info(result.getObject("LAST_NAME"));
				studentDetails = new Student();
				studentDetails.setLastName(result.getObject("LAST_NAME").toString());
				studentDetails.setFirstName(result.getObject("FIRST_NAME").toString());
				studentDetails.setEmail(result.getObject("MAIL_ID").toString());
				rows.add(studentDetails);
			}

			connection.close();
		} catch (Exception e) {
			logger.error(e);
		}
		return rows;
	}

	@Override
	public ArrayList<Student> getStudentByMailId(String studentMailId) {

		ArrayList<Student> rows = new ArrayList<Student>();
		try {
			connection = ObtainDataBaseConnection.obtainDatabaseConnection();
			query = "SELECT * FROM USER_DATABASE where (ROLE = 'Student' OR ROLE = 'TA') and MAIL_ID LIKE '%"
					+ studentMailId + "%'";
			statement = connection.prepareStatement(query);

			ResultSet result = statement.executeQuery();
			logger.info("SEARCH STUDENT QUERY EXECUTED");
			while (result.next()) {
				logger.info(result.getObject("LAST_NAME"));
				studentDetails = new Student();
				studentDetails.setLastName(result.getObject("LAST_NAME").toString());
				studentDetails.setFirstName(result.getObject("FIRST_NAME").toString());
				studentDetails.setEmail(result.getObject("MAIL_ID").toString());
				studentDetails.setUserRole(result.getObject("ROLE").toString());
				rows.add(studentDetails);
			}

			connection.close();
		} catch (Exception e) {
			logger.error(e);
		}
		return rows;
	}

	public void assignTA(String studentMailId) {

		int queryResult;
		try {
			connection = ObtainDataBaseConnection.obtainDatabaseConnection();
			query = "insert into COURSE_TA (MAIL_ID,COURSE_ID) values ('" + studentMailId + "','"
					+ CourseController.courseId + "')";
			statement = connection.prepareStatement(query);

			queryResult = statement.executeUpdate();
			System.out.println(queryResult);

			query = "update USER_DATABASE SET ROLE = 'TA' where MAIL_ID='" + studentMailId + "'";
			statement = connection.prepareStatement(query);
			queryResult = statement.executeUpdate();
			System.out.println(queryResult);

			connection.close();
			logger.info("WRITE TO DATABASE SUCCESSFUL!");
		} catch (Exception e) {
			logger.error(e);
		}
	}
}