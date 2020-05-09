package com.group3.AdminAndAuthorization.Services;

import com.group3.AdminAndAuthorization.Services.IDeleteCourseService;
import com.group3.BusinessModels.Course;
import com.group3.AdminAndAuthorization.DAO.IDeleteCourseDAO;

public class DeleteCourseService implements IDeleteCourseService {
	IDeleteCourseDAO deleteCourseDAO;
	Course course;

	public DeleteCourseService(IDeleteCourseDAO deleteCourseDAO, Course course) {
		this.deleteCourseDAO = deleteCourseDAO;
		this.course = course;
	}

	@Override
	public String deleteCourse() {
		String message = deleteCourseDAO.deleteCourse(course);
		return message;
	}
}