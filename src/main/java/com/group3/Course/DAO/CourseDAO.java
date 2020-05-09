package com.group3.Course.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.group3.BusinessModels.Course;
import com.group3.BusinessModels.Student;
import com.group3.DBConnectivity.ObtainDataBaseConnection;

public class CourseDAO implements ICourseDAO {
	Connection connection;
	String query;
	PreparedStatement statement;
	Course courseModel;

	private static Logger logger = LogManager.getLogger(CourseDAO.class);

	public ArrayList<String> getEnrolledStudentsByCourseId(String courseId) {
		ResultSet result;
		ArrayList<String> current_students = new ArrayList<String>();
		try {
			connection = ObtainDataBaseConnection.obtainDatabaseConnection();
			query = "SELECT *FROM student_enrollments where COURSE_ID='" + courseId + "'";
			statement = connection.prepareStatement(query);
			result = statement.executeQuery();

			while (result.next()) {
				logger.info(result.getObject("COURSE_ID"));
				current_students.add(result.getObject("MAIL_ID").toString());
			}

			connection.close();
			logger.info("GET ENROLLED STUDENTS QUERY EXECUTED");
		}

		catch (Exception e) {
			logger.error(e);
		}

		return current_students;
	}

	public void enrollStudentToCourse(Student studentDetails, String courseId) {
		int queryResult;
		try {
			connection = ObtainDataBaseConnection.obtainDatabaseConnection();
			query = "insert into student_enrollments (BANNER_ID,MAIL_ID,COURSE_ID) values ('"
					+ studentDetails.getBannerId() + "','" + studentDetails.getEmail() + "','" + courseId + "')";
			statement = connection.prepareStatement(query);
			queryResult = statement.executeUpdate();
			System.out.println(queryResult);

			query = "insert into USER_DATABASE (MAIL_ID,FIRST_NAME,LAST_NAME,ROLE) values ('"
					+ studentDetails.getEmail() + "','" + studentDetails.getFirstName() + "','"
					+ studentDetails.getLastName() + "','Student')";
			statement = connection.prepareStatement(query);
			queryResult = statement.executeUpdate();
			System.out.println(queryResult);

			query = "insert into AUTHENTICATION_DATABASE (MAIL_ID,PASSWORD) values ('" + studentDetails.getEmail()
					+ "','" + studentDetails.getEncryptedPassword() + "')";
			statement = connection.prepareStatement(query);
			queryResult = statement.executeUpdate();
			System.out.println(queryResult);

			connection.close();
			logger.info("ENROLL STUDENTS QUERY EXECUTED");
		}

		catch (Exception e) {
			logger.error("Error adding students to database!" + e);
		}
	}

	@Override
	public ArrayList<Course> getCoursesForGuest() {
		ResultSet result;
		ArrayList<Course> courseInfo = new ArrayList<Course>();
		try {
			connection = ObtainDataBaseConnection.obtainDatabaseConnection();
			query = "SELECT C.COURSE_ID, C.COURSE_NAME FROM COURSES C";
			logger.info(query);
			statement = connection.prepareStatement(query);

			result = statement.executeQuery();
			courseInfo = new ArrayList<Course>();

			while (result.next()) {
				logger.info(result.getObject("COURSE_ID") + ": " + result.getObject("COURSE_NAME"));
				courseModel = new Course();
				courseModel.setCourseId(result.getObject("COURSE_ID").toString());
				courseModel.setCourseName(result.getObject("COURSE_NAME").toString());
				courseInfo.add(courseModel);
			}

			connection.close();

		}

		catch (Exception e) {
			logger.error(e);
		}

		return courseInfo;
	}
}