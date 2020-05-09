package com.group3.Course.DAO;

import com.group3.BusinessModels.Course;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InstructorDAOTest {

	IDAOAbstractFactory daoInjector;
	IInstructorDAO instructorDAO;

	InsertTestDataIntoDB insert;
	RemoveTestDataFromDB remove;

	public InstructorDAOTest() {
		daoInjector = DAOAbstractFactory.instance();
		instructorDAO = daoInjector.createInstructorDAO();
		insert = new InsertTestDataIntoDB();
		remove = new RemoveTestDataFromDB();
	}

	@Test
	public void getCoursesByInstructorMailId() throws Exception {

		ArrayList<Course> courses;

		insert.InstructorDAOTest_insertEnrollment("testinstructor@dal.ca", "TestCSCI03");

		courses = new ArrayList<Course>();
		courses = instructorDAO.getCoursesByInstructorMailId("testinstructor@dal.ca");
		assertNotNull(courses);
		assertThat(courses).isNotEmpty();
		assertEquals(courses.get(0).getCourseId(), "TestCSCI03");
		assertEquals(courses.get(0).getCourseName(), "TEST");
		assertEquals(courses.size(), 1);

		remove.InstructorDAOTest_removeEnrollment("testinstructor@dal.ca", "TestCSCI03");
	}
}