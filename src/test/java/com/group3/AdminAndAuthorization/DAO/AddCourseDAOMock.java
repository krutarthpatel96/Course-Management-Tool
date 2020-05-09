package com.group3.AdminAndAuthorization.DAO;

import java.util.ArrayList;

import com.group3.AdminAndAuthorization.DAO.IAddCourseDAO;
import com.group3.BusinessModels.Course;

public class AddCourseDAOMock implements IAddCourseDAO {
	ArrayList<Course> courseList;

	AddCourseDAOMock() {

		courseList = new ArrayList<>();

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

	@Override
	public String addCourse(Course course) {

		String courseName = course.getCourseName();
		String courseId = course.getCourseId();
		String feedbackMessage = courseName + " with " + courseId + " created successfully";
		courseList.add(course);
		return feedbackMessage;
	}

	@Override
	public String isCourseExist(String courseId) {

		String feedbackMessage = new String();
		for (Course course : courseList) {
			System.out.println(course.getCourseId());
			if (course.getCourseId().equals(courseId)) {
				feedbackMessage = "Course Name  " + course.getCourseName() + " with " + "Course Id "
						+ course.getCourseId() + " already exists !! ";
				break;
			}
		}

		System.out.println(feedbackMessage);
		return feedbackMessage;
	}
}