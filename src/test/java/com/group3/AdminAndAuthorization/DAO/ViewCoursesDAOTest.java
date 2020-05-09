package com.group3.AdminAndAuthorization.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import com.group3.AdminAndAuthorization.DAO.IAddCourseDAO;
import com.group3.AdminAndAuthorization.DAO.IDeleteCourseDAO;
import com.group3.AdminAndAuthorization.DAO.IViewCoursesDAO;
import com.group3.AdminAndAuthorization.DAO.DAOAbstractFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.group3.BusinessModels.Course;

class ViewCoursesDAOTest {
	IDAOAbstractFactory daoInjector;
	IViewCoursesDAO viewCourseDAO;
	IDeleteCourseDAO deleteCourseDAO;
	IAddCourseDAO addCourseDAO;
	Course testCourse;
	ArrayList<Course> courseList;

	public ViewCoursesDAOTest() {

		courseList = new ArrayList<>();
		daoInjector = DAOAbstractFactory.instance();
		addCourseDAO = daoInjector.createAddCourseDAO();
		deleteCourseDAO = daoInjector.createDeleteCourseDAO();
		viewCourseDAO = daoInjector.createViewCourseDAO();

		testCourse = new Course();
		testCourse.setCourseId("CSCT7777");
		testCourse.setCourseName("Computational Biotechnology II");
	}

	@Test
	final void testGetAllCourses() {
		
		courseList = this.viewCourseDAO.getAllCourses();
		for (Course course : courseList) {
			assertNotEquals(course.getCourseId(), testCourse.getCourseId());
		}
		
		this.addCourseDAO.addCourse(testCourse);
		courseList = this.viewCourseDAO.getAllCourses();
		
		for (Course course : courseList) {
			if (course.getCourseName().equals(testCourse.getCourseName())) {
				assertEquals(testCourse.getCourseId(), course.getCourseId());
			}
		}
		this.deleteCourseDAO.deleteCourse(testCourse);
	}
}