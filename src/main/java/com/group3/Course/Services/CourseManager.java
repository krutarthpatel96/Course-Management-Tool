package com.group3.Course.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.group3.Course.DAO.*;
import com.group3.BusinessModels.Course;

public class CourseManager implements ICourseManager {
	Connection conn;
	PreparedStatement statement;
	Course courseModel;
	IDAOAbstractFactory daoInjector;
	ICourseDAO courseDAO;
	ITADAO taDAO;
	IInstructorDAO instructorDAO;
	ArrayList<Course> courseInfo;

	private static Logger logger = LogManager.getLogger(CourseManager.class);

	public CourseManager(IDAOAbstractFactory daoInjector) {

		this.daoInjector = daoInjector;
		this.courseDAO = daoInjector.createCourseDAO();
		this.taDAO = daoInjector.createTADAO();
		this.instructorDAO = daoInjector.createInstructorDAO();
	}

	@Override
	public ArrayList<Course> getCoursesByTAMailId(String studentMailId) {

		courseInfo = new ArrayList<Course>();
		courseInfo = taDAO.getCoursesByTAMailId(studentMailId);
		return courseInfo;
	}

	@Override
	public ArrayList<Course> getCoursesByInstructorMailId(String instructorMailId) {

		courseInfo = new ArrayList<Course>();
		courseInfo = instructorDAO.getCoursesByInstructorMailId(instructorMailId);
		return courseInfo;
	}

	@Override
	public ArrayList<Course> getCoursesForGuest() {

		courseInfo = new ArrayList<Course>();
		courseInfo = courseDAO.getCoursesForGuest();
		return courseInfo;
	}

}