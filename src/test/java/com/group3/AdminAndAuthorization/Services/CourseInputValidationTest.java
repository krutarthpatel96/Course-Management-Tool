package com.group3.AdminAndAuthorization.Services;

import static org.junit.jupiter.api.Assertions.*;

import com.group3.AdminAndAuthorization.Services.ServiceAbstractFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.group3.BusinessModels.Course;
import com.group3.AdminAndAuthorization.Services.ICourseInputValidation;

class CourseInputValidationTest {
	ICourseInputValidation courseInputValidation;
	String courseId, courseName, courseIdErrorMesssage, courseNameErrorMesssage;
	Course course;

	public CourseInputValidationTest() {
		course = new Course();
		courseInputValidation = ServiceAbstractFactory.instance().createCourseInputValidation();
		courseIdErrorMesssage = "Invalid Course Id (It Should Like : csci5308)";
		courseNameErrorMesssage = "Invalid Course Name (It Should start with String only)";
	}

	@Test
	final void testValidateInputCourseInValidCourseId() {

		courseId = "CSC6400";
		courseName = "Virtual Reality";
		course.setCourseId(courseId);
		course.setCourseName(courseName);

		assertTrue(courseInputValidation.validateInputCourse(course).get(0).equals(courseIdErrorMesssage),
				courseIdErrorMesssage);
	}

	@Test
	public void testValidateInputCourseValidCourseId() {

		courseId = "CSCI5308";
		courseName = "QA";
		course.setCourseId(courseId);
		course.setCourseName(courseName);
		assertTrue(courseInputValidation.validateInputCourse(course).size() == 0, "No Error condition");
		assertFalse(courseInputValidation.validateInputCourse(course).size() > 0, "No Error condition");
	}

	@Test
	public void testValidateInputCourseInValidCourseName() {
		courseId = "CSCI5308";
		courseName = "12345QA";
		course.setCourseId(courseId);
		course.setCourseName(courseName);
		assertTrue(courseInputValidation.validateInputCourse(course).get(0).equals(courseNameErrorMesssage),
				courseNameErrorMesssage);
	}

	@Test
	public void testValidateInputCourseValidCourseName() {
		courseId = "CSCI5308";
		courseName = "Quality Assurance";
		course.setCourseId(courseId);
		course.setCourseName(courseName);
		assertTrue(courseInputValidation.validateInputCourse(course).size() == 0, "No Error condition");
		assertFalse(courseInputValidation.validateInputCourse(course).size() > 0, "No Error condition");
	}
}
