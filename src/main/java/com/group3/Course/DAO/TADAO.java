package com.group3.Course.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.group3.BusinessModels.Course;
import com.group3.DBConnectivity.ObtainDataBaseConnection;

public class TADAO implements ITADAO {
	Connection connection;
	String query;
	private static Logger logger = LogManager.getLogger(CourseDAO.class);
	PreparedStatement statement;
	Course courseModel;
	ArrayList<Course> courseInfo;

	public ArrayList<Course> getCoursesByTAMailId(String studentMailId) {

		courseInfo = new ArrayList<Course>();
		try {
			connection = ObtainDataBaseConnection.obtainDatabaseConnection();
			String query = "SELECT C.COURSE_ID, C.COURSE_NAME" + " FROM COURSES C"
					+ " JOIN student_enrollments S ON S.COURSE_ID=C.COURSE_ID" + " WHERE S.MAIL_ID=\"" + studentMailId
					+ "\"";

			logger.info(query);
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			courseInfo = new ArrayList<Course>();

			while (result.next()) {
				logger.info(result.getObject("COURSE_ID") + ": " + result.getObject("COURSE_NAME"));
				courseModel = new Course();
				courseModel.setCourseId(result.getObject("COURSE_ID").toString());
				courseModel.setCourseName(result.getObject("COURSE_NAME").toString());
				courseInfo.add(courseModel);
			}

			connection.close();
		} catch (Exception e) {
			logger.error(e);
		}
		return courseInfo;
	}
}