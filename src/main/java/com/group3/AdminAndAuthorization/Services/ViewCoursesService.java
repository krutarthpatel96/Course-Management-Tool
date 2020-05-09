package com.group3.AdminAndAuthorization.Services;

import java.util.ArrayList;

import com.group3.AdminAndAuthorization.Services.IViewCoursesService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.group3.BusinessModels.Course;
import com.group3.AdminAndAuthorization.DAO.IViewCoursesDAO;

public class ViewCoursesService implements IViewCoursesService {
	private ArrayList<Course> courses;
	private IViewCoursesDAO viewCoursesDAO;
	private static Logger logger = LogManager.getLogger(ViewCoursesService.class);

	public ViewCoursesService(IViewCoursesDAO iViewCoursesDAO) {
		this.viewCoursesDAO = iViewCoursesDAO;
	}

	@Override
	public ArrayList<Course> getAllCourses() {
		courses = this.viewCoursesDAO.getAllCourses();
		if (courses.isEmpty()) {
			logger.info("No courses to display at all ");
		}

		return courses;
	}
}