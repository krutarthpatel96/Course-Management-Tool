package com.group3.AdminAndAuthorization.DAO;

import java.util.ArrayList;

import com.group3.AdminAndAuthorization.DAO.IViewCoursesDAO;
import com.group3.BusinessModels.Course;

public class ViewCoursesDAOMock implements IViewCoursesDAO {

	ArrayList<Course> courseList;

	public ViewCoursesDAOMock() {
		courseList = new ArrayList<>();
	}

	@Override
	public ArrayList<Course> getAllCourses() {
		setCourses();
		return courseList;
	}

	public void setCourses() {

		Course course = new Course();
		course.setCourseId("CSCI7800");
		course.setCourseName("Advanced Algorithms");
		courseList.add(course);

		course = new Course();
		course.setCourseId("CSCI5308");
		course.setCourseName("Quality Assurance");
		courseList.add(course);

		course = new Course();
		course.setCourseId("CSCI8000");
		course.setCourseName("Pattern Recognition");
		courseList.add(course);
	}
}