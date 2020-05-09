package com.group3.AdminAndAuthorization.Services;

import static org.junit.jupiter.api.Assertions.*;

import com.group3.AdminAndAuthorization.Services.IExtractCourseIdService;
import org.junit.jupiter.api.Test;

class ExtractCourseIdServiceTest {
	IExtractCourseIdService extractCourseIdService;

	@Test
	final void testExtractCourseId() {

		final String inputString = "csci6000-Artificial Intelligence";
		String expectedOutcome = "csci6000-";
		extractCourseIdService = ServiceAbstractFactory.instance().createExtractCourseIdService(inputString);
		System.out.println("preString " + inputString);
		assertFalse(extractCourseIdService.extractCourseId().equals(expectedOutcome));
		expectedOutcome = "csci6000";
		extractCourseIdService = ServiceAbstractFactory.instance().createExtractCourseIdService(inputString);
		assertTrue(extractCourseIdService.extractCourseId().equals(expectedOutcome));
	}
}
