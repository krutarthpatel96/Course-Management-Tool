package com.group3.Course.Services;

import com.group3.BusinessModels.Course;
import com.group3.Course.DAO.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseManagerTest {

	IDAOAbstractFactory daoInjector;
	ITADAO taDAO;
	IInstructorDAO instructorDAO;
	ICourseDAO courseDAO;
	ArrayList<Course> courseInfo;

	public CourseManagerTest() {
		daoInjector = DAOMockAbstractFactory.instance();
		taDAO = daoInjector.createTADAO();
		instructorDAO = daoInjector.createInstructorDAO();
		courseDAO = daoInjector.createCourseDAO();
	}

	@BeforeEach
	public void init() {
		courseInfo = new ArrayList<Course>();
	}

	@Test
	public void getCoursesByTAMailIdTest() {
		courseInfo = taDAO.getCoursesByTAMailId("tstark@dal.ca");
		assertNotNull(courseInfo);
		assertThat(courseInfo).isNotEmpty();
		assertEquals(courseInfo.size(), 1);
	}

	@Test
	public void getCoursesByInstructorMailIdTest() {
		courseInfo = instructorDAO.getCoursesByInstructorMailId("srogers@dal.ca");
		assertNotNull(courseInfo);
		assertThat(courseInfo).isNotEmpty();
		assertEquals(courseInfo.size(), 1);
	}

	@Test
	public void getCoursesForGuestTest() {
		courseInfo = courseDAO.getCoursesForGuest();
		assertNotNull(courseInfo);
		assertThat(courseInfo).isNotEmpty();
		assertEquals(courseInfo.size(), 1);
	}
}