package com.group3.AdminAndAuthorization.DAO;

import java.util.ArrayList;

import com.group3.AdminAndAuthorization.DAO.IDeleteCourseDAO;
import com.group3.BusinessModels.Course;

public class DeleteCourseDAOMock implements IDeleteCourseDAO {

	ArrayList<Course> courseList;
	String feedbackMessage;

	public DeleteCourseDAOMock() {

		feedbackMessage = new String();
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
	public String deleteCourse(Course course) {

		for (Course course1 : courseList) {
			if (course.getCourseId().equals(course1.getCourseId())) {
				feedbackMessage = course.getCourseName() + " (" + course.getCourseId() + ") "
						+ " is deleted sucessfully ";
				courseList.remove(course1);
				break;
			} else {
				feedbackMessage = "Error occured while deleting the course";
			}
		}
		return feedbackMessage;
	}
}