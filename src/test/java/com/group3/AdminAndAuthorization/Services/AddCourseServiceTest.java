package com.group3.AdminAndAuthorization.Services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import com.group3.AdminAndAuthorization.DAO.IAddCourseDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.group3.BusinessModels.Course;
import com.group3.AdminAndAuthorization.DAO.DAOMockAbstractFactory;
import com.group3.AdminAndAuthorization.*;
import com.group3.AdminAndAuthorization.Services.ServiceAbstractFactory;
import com.group3.AdminAndAuthorization.Services.IAddCourseService;
import com.group3.AdminAndAuthorization.Services.ICourseInputValidation;

class AddCourseServiceTest {
	IAddCourseService addCourseService;
	ICourseInputValidation courseInputValidation;
	ArrayList<String> feedbackMessage;

	public AddCourseServiceTest() {

		IAddCourseDAO addCourseDAO = DAOMockAbstractFactory.instance().createAddCourseDAO();
		addCourseService = ServiceAbstractFactory.instance().createaddCourseService(addCourseDAO);
		courseInputValidation = ServiceAbstractFactory.instance().createCourseInputValidation();
		feedbackMessage = new ArrayList<>();
	}

	@Test
	final void testAddCourseService() {

		String expectedErrorMessage;
		feedbackMessage = new ArrayList<>();
		Course course = new Course();
		course.setCourseId("CS135"); // False Pattern for course
		course.setCourseName("Software Engineering");
		feedbackMessage = addCourseService.insertCourseDetails(course, courseInputValidation);
		expectedErrorMessage = "Invalid Course Id (It Should Like : csci5308)";
		assertTrue(feedbackMessage.get(0).equals(expectedErrorMessage));
	}

	@Test
	final void testAddCourseServiceAlreadyExist() {

		IAddCourseDAO addCourseDAO;
		String expectedResponse;
		feedbackMessage = new ArrayList<>();
		Course course = new Course();
		course.setCourseId("CSCI5308");
		course.setCourseName("Quality Assurance");

		expectedResponse = "Course Name  " + course.getCourseName() + " with " + "Course Id " + course.getCourseId()
				+ " already exists !! ";
		addCourseDAO = DAOMockAbstractFactory.instance().createAddCourseDAO();
		feedbackMessage = addCourseService.insertCourseDetails(course, courseInputValidation);
		addCourseService = ServiceAbstractFactory.instance().createaddCourseService(addCourseDAO);
		System.out.println(feedbackMessage.get(0) + "\n" + expectedResponse + " "
				+ feedbackMessage.get(0).equals(expectedResponse));
		assertTrue(feedbackMessage.get(0).equals(expectedResponse));
	}

	@Test
	final void testAddCourseServiceCreatedSuccess() {

		String expectedResponse;
		feedbackMessage = new ArrayList<>();
		Course course = new Course();
		course.setCourseId("CSCT6707");
		course.setCourseName("Advanced Game Development");
		feedbackMessage = addCourseService.insertCourseDetails(course, courseInputValidation);
		expectedResponse = course.getCourseName() + " with " + course.getCourseId() + " created successfully";
		System.out.println(" CC : " + feedbackMessage.get(0));
		System.out.println(expectedResponse);
		assertTrue(feedbackMessage.get(0).equals(expectedResponse));
	}
}
