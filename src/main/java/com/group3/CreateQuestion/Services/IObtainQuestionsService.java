package com.group3.CreateQuestion.Services;

import java.util.List;

import com.group3.BusinessModels.Instructor;

public interface IObtainQuestionsService {
	public List<List<String>> obtainInstructorQuestions(Instructor instructor, String order);
}
