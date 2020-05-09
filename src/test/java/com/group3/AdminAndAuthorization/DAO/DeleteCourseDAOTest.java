package com.group3.AdminAndAuthorization.DAO;

import static org.junit.jupiter.api.Assertions.*;

import com.group3.AdminAndAuthorization.DAO.IAddCourseDAO;
import com.group3.AdminAndAuthorization.DAO.IDeleteCourseDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.group3.BusinessModels.Course;

class DeleteCourseDAOTest {

	private IAddCourseDAO iaddCourseDAO;
	private IDeleteCourseDAO iDeleteCourseDAO;
	private Course course;
	private String courseName, courseId;

	public DeleteCourseDAOTest() {
	
		course = new Course();
		courseName = "Penentration Testing(Cyber)";
		courseId = "CSCT6786";
		course.setCourseId(courseId);
		course.setCourseName(courseName);
		IDAOAbstractFactory daoinjector = DAOAbstractFactory.instance();
		iaddCourseDAO = daoinjector.createAddCourseDAO();
		iDeleteCourseDAO = daoinjector.createDeleteCourseDAO();
	}

	@Test
	final void testDeleteCourse() {
		String expectedOutCome = course.getCourseName() + " (" + course.getCourseId() + ") "
				+ " is deleted sucessfully ";
		assertFalse(this.iDeleteCourseDAO.deleteCourse(course).equals(expectedOutCome));
		this.iaddCourseDAO.addCourse(course);
		assertTrue(this.iDeleteCourseDAO.deleteCourse(course).equals(expectedOutCome));
	}
}