package com.group3.CreateQuestion.DAO;

import java.util.List;

public interface IRetrieveQuestionsDAO {
	public List<List<String>> getQuestionsByInstructorID(String instructorId, String order);
}
