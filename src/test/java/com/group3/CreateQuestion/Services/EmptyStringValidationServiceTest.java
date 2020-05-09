package com.group3.CreateQuestion.Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmptyStringValidationServiceTest {

	EmptyStringValidationService emptyStringValidationService;

	public EmptyStringValidationServiceTest() {

		emptyStringValidationService = new EmptyStringValidationService();
	}

	@Test
	void isValid() {

		String emptyString = new String();
		assertFalse(emptyStringValidationService.isValid(emptyString));
		String nonEmptyString = "Hello I am Non empty ! geeks";
		assertTrue(emptyStringValidationService.isValid(nonEmptyString));
	}
}