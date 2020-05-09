package com.group3.Course.DAO;

import com.group3.BusinessModels.Course;
import com.group3.BusinessModels.Student;
import com.group3.DBConnectivity.ObtainDataBaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class InsertTestDataIntoDB {

	Connection connection;
	String query;
	private static Logger logger = LogManager.getLogger(CourseDAO.class);
	PreparedStatement statement;
	Course courseModel;
	ArrayList<Course> courseInfo;
	int result;

	public void InstructorDAOTest_insertEnrollment(String instId, String courseId) throws Exception {

		connection = ObtainDataBaseConnection.obtainDatabaseConnection();
		query = "insert into COURSES values ('" + courseId + "','TEST')";
		statement = connection.prepareStatement(query);
		result = statement.executeUpdate();
		System.out.println("Test Course ID Inserted!");

		query = "insert into ALLOCATE_INSTRUCTOR values ('" + instId + "','" + courseId + "')";
		statement = connection.prepareStatement(query);
		result = statement.executeUpdate();
		System.out.println("Test Course ID Inserted!");
		connection.close();

	}

	public void TADAOTest_insertEnrollment(Student student, String courseId) throws Exception {

		connection = ObtainDataBaseConnection.obtainDatabaseConnection();
		query = "insert into COURSES values ('" + courseId + "','TEST');";
		statement = connection.prepareStatement(query);
		result = statement.executeUpdate();
		System.out.println("Test Course ID Inserted!");

		query = "insert into student_enrollments values ('" + student.getBannerId() + "','" + student.getEmail() + "','"
				+ courseId + "')";
		statement = connection.prepareStatement(query);
		result = statement.executeUpdate();
		System.out.println("Test Course ID Inserted!");
		connection.close();
	}

	public void StudentDAOTest_insertStudent(Student student) throws Exception {

		connection = ObtainDataBaseConnection.obtainDatabaseConnection();
		query = "insert into USER_DATABASE values ('" + student.getLastName() + "','" + student.getFirstName()
				+ "','Student','" + student.getEmail() + "');";
		statement = connection.prepareStatement(query);
		result = statement.executeUpdate();
		System.out.println("Test User Inserted!");
		connection.close();
	}

	public void StudentDAOTest_updateTA(Student student) throws Exception {

		connection = ObtainDataBaseConnection.obtainDatabaseConnection();
		query = "update USER_DATABASE set ROLE = 'TA' where MAIL_ID = '" + student.getEmail() + "';";
		statement = connection.prepareStatement(query);
		result = statement.executeUpdate();
		System.out.println("Test User Inserted!");
		connection.close();
	}

}