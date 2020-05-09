package com.group3.CreateQuestion.Services;

import java.sql.Timestamp;
import java.util.Date;

public class CurrentTimeStampGenerationService implements ICurrentTimeStampGenerationService {

	@Override
	public Timestamp returnCurrentTimeStamp() {

		Date date = new Date();
		long time = date.getTime();
		Timestamp timestamp = new Timestamp(time);
		return timestamp;
	}
}