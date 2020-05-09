package com.group3.AdminAndAuthorization.Services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import com.group3.AdminAndAuthorization.Services.IViewCoursesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.group3.AdminAndAuthorization.DAO.DAOMockAbstractFactory;
import com.group3.AdminAndAuthorization.DAO.IDAOAbstractFactory;
import com.group3.AdminAndAuthorization.DAO.IViewCoursesDAO;
import com.group3.BusinessModels.Course;;

class ViewCoursesServiceTest {
	IDAOAbstractFactory daoInjector;
	IViewCoursesDAO viewCoursesDAO;
	IViewCoursesService viewCoursesService;
	ArrayList<Course> courseList;

	public ViewCoursesServiceTest() {
		daoInjector = DAOMockAbstractFactory.instance();
		viewCoursesDAO = daoInjector.createViewCourseDAO();
	}

	@Test
	final void testGetAllCourses() {

		viewCoursesService = ServiceAbstractFactory.instance().createViewCoursesService(viewCoursesDAO);
		courseList = viewCoursesService.getAllCourses();
		assertFalse(courseList.isEmpty());
		assertTrue(courseList.size() == 3);
	}
}