package com.group3.CreateQuestion.Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StartWithSpecialSymbolValidationServiceTest {

	StartWithSpecialSymbolValidationService startWithSpecialSymbolValidationService;

	public StartWithSpecialSymbolValidationServiceTest() {

		startWithSpecialSymbolValidationService = new StartWithSpecialSymbolValidationService();
	}

	@Test
	void isValid() {

		assertFalse(!startWithSpecialSymbolValidationService.isValid("@!,amnd,mn"));
		assertTrue(startWithSpecialSymbolValidationService.isValid("literals $"));
	}
}