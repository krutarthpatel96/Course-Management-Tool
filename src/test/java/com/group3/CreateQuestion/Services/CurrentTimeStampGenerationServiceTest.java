package com.group3.CreateQuestion.Services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CurrentTimeStampGenerationServiceTest {

	private IServiceAbstractFactory serviceAbstractFactory;
	private ICurrentTimeStampGenerationService currentTimeStampGenerationService;
	public static Logger logger = LogManager.getLogger(CurrentTimeStampGenerationServiceTest.class);

	public CurrentTimeStampGenerationServiceTest() {

		serviceAbstractFactory = ServiceAbstractFactory.instance();
		currentTimeStampGenerationService = serviceAbstractFactory.createCurrentTimeStampGenerationService();
	}

	@Test
	void returnCurrentTimeStamp() {

		Timestamp timestamp = currentTimeStampGenerationService.returnCurrentTimeStamp();
		logger.info(timestamp);
		assertFalse(timestamp == null);
		assertTrue(timestamp != null);
	}
}