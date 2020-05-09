package com.group3.CreateQuestion.DAO;

import com.group3.CreateQuestion.Services.ICurrentTimeStampGenerationService;

import java.util.Random;

public class SaveBasicQuestionInformationDAOMock implements ISaveBasicQuestionInformationDAO {

	ICurrentTimeStampGenerationService currentTimeStampGenerationService;

	public SaveBasicQuestionInformationDAOMock(ICurrentTimeStampGenerationService currentTimeStampGenerationService) {
		this.currentTimeStampGenerationService = currentTimeStampGenerationService;
	}

	@Override
	public String saveDetailsAndReturnId(String title, String text, String type) {

		Random random = new Random();
		int randomNumber = random.nextInt(100);
		String id = String.valueOf(randomNumber);
		return id;
	}

}