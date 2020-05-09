package com.group3.Course.DAO;

import com.group3.BusinessModels.Course;
import com.group3.BusinessModels.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CourseDAOTest {

	IDAOAbstractFactory daoInjector;
	ICourseDAO courseDAO;
	RemoveTestDataFromDB remove;
	ArrayList<String> enrolledStudentMailIds;

	public CourseDAOTest() {

		daoInjector = DAOAbstractFactory.instance();
		courseDAO = daoInjector.createCourseDAO();
		remove = new RemoveTestDataFromDB();
	}

	@Test
	public void getEnrolledStudentsByCourseId() throws Exception {

		Student student = new Student();
		student.setBannerId("B001234");
		student.setEmail("test@dal.ca");

		courseDAO.enrollStudentToCourse(student, "CSCI00");

		enrolledStudentMailIds = courseDAO.getEnrolledStudentsByCourseId("CSCI00");
		assertNotNull(enrolledStudentMailIds);
		assertThat(enrolledStudentMailIds).isNotEmpty();
		assertEquals(enrolledStudentMailIds.size(), 1);

		remove.CourseEnrollmentsDAOTest_removeEnrollment(student, "CSCI00");
	}

	@Test
	public void enrollStudentToCourse() throws Exception {

		Student student = new Student();
		student.setBannerId("B0012345");
		student.setFirstName("Test");
		student.setLastName("Test");
		student.setEncryptedPassword("Test");
		student.setUserRole("Student");
		student.setEmail("test2@dal.ca");

		courseDAO.enrollStudentToCourse(student, "CSCI01");
		enrolledStudentMailIds = courseDAO.getEnrolledStudentsByCourseId("CSCI01");
		assertNotNull(enrolledStudentMailIds);
		assertThat(enrolledStudentMailIds).isNotEmpty();
		assertEquals(enrolledStudentMailIds.size(), 1);

		remove.CourseEnrollmentsDAOTest_removeEnrollment(student, "CSCI01");

	}

	@Test
	public void getCoursesForGuest() throws Exception {

		ArrayList<Course> enrolledStudentMailIds = new ArrayList<Course>();
		enrolledStudentMailIds = courseDAO.getCoursesForGuest();
		assertNotNull(enrolledStudentMailIds);
		assertThat(enrolledStudentMailIds).isNotEmpty();
		assertTrue(enrolledStudentMailIds.size() > 0);

	}

}