package com.group3.BusinessModels;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

	Course testcourse;

	@Test
	final void testGetCourseID() {

		testcourse = new Course();
		assertNull(testcourse.getCourseId());
		testcourse.setCourseId("CSCI5308");
		assertNotNull(testcourse.getCourseId());
	}

	@Test
	final void testSetCourseID() {

		testcourse = new Course();
		testcourse.setCourseId("CSCI5608");
		assertNotEquals("CSCI5408", testcourse.getCourseId());
		assertEquals("CSCI5608", testcourse.getCourseId());
	}

	@Test
	final void testGetCourseName() {

		testcourse = new Course();
		assertNull(testcourse.getCourseName());
		testcourse.setCourseName("Cyber Security");
		assertNotNull(testcourse.getCourseName());
	}

	@Test
	final void testSetCourseName() {

		testcourse = new Course();
		testcourse.setCourseName("Coordinate Geomentry Applications");
		assertNotEquals("Coordinate Geomentry", testcourse.getCourseName());
		assertEquals("Coordinate Geomentry Applications", testcourse.getCourseName());
	}
}
