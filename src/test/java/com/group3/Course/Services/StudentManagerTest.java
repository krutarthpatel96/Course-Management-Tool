package com.group3.Course.Services;

import com.group3.BusinessModels.Student;
import com.group3.Course.DAO.DAOMockAbstractFactory;
import com.group3.Course.DAO.ICourseDAO;
import com.group3.Course.DAO.IDAOAbstractFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StudentManagerTest {

	IDAOAbstractFactory daoInjector;
	ICourseDAO courseDAO;
	ArrayList<String> current_students;

	public StudentManagerTest() {

		daoInjector = DAOMockAbstractFactory.instance();
		courseDAO = daoInjector.createCourseDAO();
	}

	@Test
	void addStudentsToCourseTest() {

		Student studentDetails = new Student();
		studentDetails.setEmail("jwick@dal.ca");
		courseDAO.enrollStudentToCourse(studentDetails, "2");
		current_students = courseDAO.getEnrolledStudentsByCourseId("1");
		assertNotNull(current_students);
		assertThat(current_students).isNotEmpty();
		assertEquals(current_students.size(), 1);
	}
}