package com.group3.AdminAndAuthorization.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.group3.BusinessModels.Course;
import com.group3.AdminAndAuthorization.Services.IServiceAbstractFactory;
import com.group3.AdminAndAuthorization.Services.IViewCoursesService;
import com.group3.AdminAndAuthorization.Services.ServiceAbstractFactory;

class AddCourseDAOTest {
	IDAOAbstractFactory daoInjector;
	IServiceAbstractFactory serviceInjector;
	IAddCourseDAO addCourseDAO;
	IDeleteCourseDAO deleteCourseDAO;
	IViewCoursesDAO viewCoursesDAO;
	IViewCoursesService viewCoursesService;
	Course course;
	String courseName, courseId;
	ArrayList<Course> courseList;

	public AddCourseDAOTest() {

		course = new Course();
		courseName = "Computational Biology";
		courseId = "CSCT6748";
		course.setCourseId(courseId);
		course.setCourseName(courseName);

		daoInjector = DAOAbstractFactory.instance();
		addCourseDAO = daoInjector.createAddCourseDAO();
		viewCoursesDAO = daoInjector.createViewCourseDAO();
		serviceInjector = ServiceAbstractFactory.instance();
		viewCoursesService = serviceInjector.createViewCoursesService(viewCoursesDAO);

		courseList = viewCoursesService.getAllCourses();
		deleteCourseDAO = daoInjector.createDeleteCourseDAO();
	}

	@Test
	final void testAddCourseCreatedSuccessCase() {

		String expectedOutcome = courseName + " with " + courseId + " created successfully";
		String outcome = this.addCourseDAO.addCourse(course);
		assertEquals(expectedOutcome, outcome);
		deleteCourseDAO.deleteCourse(course);
	}

	@Test
	final void testIsCourseExist() {
		
		String expectedOutcome;
		if (courseList.size() > 0) {
			expectedOutcome = "Course Name  " + courseList.get(0).getCourseName() + " with " + "Course ID "
					+ courseList.get(0).getCourseId() + " already exists !! ";
			assertTrue(this.addCourseDAO.isCourseExist(courseList.get(0).getCourseId()).equals(expectedOutcome));
		}
	}
}