package com.group3.Course.DAO;

import com.group3.BusinessModels.Course;
import com.group3.BusinessModels.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TADAOTest {

	IDAOAbstractFactory daoInjector;
	ITADAO taDAO;

	InsertTestDataIntoDB insert;
	RemoveTestDataFromDB remove;

	public TADAOTest() {
		daoInjector = DAOAbstractFactory.instance();
		taDAO = daoInjector.createTADAO();
		insert = new InsertTestDataIntoDB();
		remove = new RemoveTestDataFromDB();
	}

	@Test
	public void getCoursesByTAMailId() throws Exception {

		ArrayList<Course> courses;
		Student student = new Student();
		student.setBannerId("B009876");
		student.setEmail("test1234@dal.ca");

		insert.TADAOTest_insertEnrollment(student, "CSCI04");

		courses = new ArrayList<Course>();
		courses = taDAO.getCoursesByTAMailId("test1234@dal.ca");
		assertNotNull(courses);
		assertThat(courses).isNotEmpty();
		assertEquals(courses.get(0).getCourseId(), "CSCI04");
		assertEquals(courses.get(0).getCourseName(), "TEST");
		assertEquals(courses.size(), 1);

		remove.TADAOTest_removeEnrollment(student, "CSCI04");
	}
}