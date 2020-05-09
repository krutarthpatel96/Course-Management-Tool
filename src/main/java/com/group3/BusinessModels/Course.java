package com.group3.BusinessModels;

import java.util.ArrayList;
import com.group3.Course.DAO.IDAOAbstractFactory;
import com.group3.Course.Services.*;

public class Course {
	String courseId;
	String courseName;

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public ArrayList<Course> getInstructorCourses(IDAOAbstractFactory daoInjector, String instructorId) {
		ArrayList<Course> courses = new ArrayList<Course>();
		ICourseManager courseManager = ServiceAbstractFactory.instance().createCourseManager(daoInjector);
		courses = courseManager.getCoursesByInstructorMailId(instructorId);
		return courses;
	}
}