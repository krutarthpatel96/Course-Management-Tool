package com.group3.BusinessModels;

import java.util.ArrayList;

import com.group3.Course.DAO.IDAOAbstractFactory;
import com.group3.Course.Services.ICourseManager;
import com.group3.Course.Services.ServiceAbstractFactory;

public class Guest extends Person {
	public Guest(String lastName, String firstName, String email, String role, String psw) {
		super(lastName, firstName, email, role, psw);
	}

	public Guest() {
		super();
	}

	public ArrayList<Course> getCourses(IDAOAbstractFactory daoInjector) {
		ArrayList<Course> courses = new ArrayList<Course>();
		ICourseManager courseManager = ServiceAbstractFactory.instance().createCourseManager(daoInjector);
		courses = courseManager.getCoursesForGuest();
		return courses;
	}
}