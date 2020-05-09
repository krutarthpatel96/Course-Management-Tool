package com.group3.AdminAndAuthorization.Services;

import static org.junit.jupiter.api.Assertions.*;

import com.group3.AdminAndAuthorization.Services.IDeleteCourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.group3.BusinessModels.Course;

import com.group3.AdminAndAuthorization.DAO.DAOMockAbstractFactory;
import com.group3.AdminAndAuthorization.DAO.IDAOAbstractFactory;
import com.group3.AdminAndAuthorization.DAO.IDeleteCourseDAO;

class DeleteCourseServiceTest {
	private Course course;
	private IDeleteCourseService deleteCourseService;
	private IDeleteCourseDAO deleteCourseDAO;

	public DeleteCourseServiceTest() {
		deleteCourseDAO = DAOMockAbstractFactory.instance().createDeleteCourseDAO();
	}

	@Test
	final void testDeleteCourse() {

		String feedbackString;
		String expectedResponse;

		course = new Course();
		course.setCourseId("CSCI7000");
		course.setCourseName("Software Architecture");
		deleteCourseService = ServiceAbstractFactory.instance().createDeleteCourseService(deleteCourseDAO, course);

		feedbackString = deleteCourseService.deleteCourse();
		expectedResponse = "Error occured while deleting the course";
		assertTrue(feedbackString.equals(expectedResponse) == true);

		course.setCourseId("CSCI7800");
		course.setCourseName("Advanced Algorithms");
		deleteCourseService = ServiceAbstractFactory.instance().createDeleteCourseService(deleteCourseDAO, course);
		feedbackString = deleteCourseService.deleteCourse();
		expectedResponse = course.getCourseName() + " (" + course.getCourseId() + ") " + " is deleted sucessfully ";
		assertTrue(expectedResponse.equals(feedbackString) == true);
	}
}
