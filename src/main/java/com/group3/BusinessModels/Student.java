package com.group3.BusinessModels;

import java.util.ArrayList;

import com.group3.Course.DAO.IDAOAbstractFactory;
import com.group3.Course.Services.ICourseManager;
import com.group3.Course.Services.ServiceAbstractFactory;

public class Student extends Person {
	String bannerId;

	public String getBannerId() {
		return bannerId;
	}

	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}

	public ArrayList<Course> getStudentCourses(IDAOAbstractFactory daoInjector) {
		ArrayList<Course> courses = new ArrayList<Course>();
		ICourseManager courseManager = ServiceAbstractFactory.instance().createCourseManager(daoInjector);
		courses = courseManager.getCoursesForGuest();
		return courses;
	}

	public ArrayList<Course> getTACourses(IDAOAbstractFactory daoInjector, String studentId) {
		ArrayList<Course> courses = new ArrayList<Course>();
		ICourseManager courseManager = ServiceAbstractFactory.instance().createCourseManager(daoInjector);
		courses = courseManager.getCoursesByTAMailId(studentId);
		return courses;
	}
}