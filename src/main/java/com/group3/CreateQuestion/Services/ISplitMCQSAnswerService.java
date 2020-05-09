package com.group3.CreateQuestion.Services;

import com.group3.CreateQuestion.BusinessModels.MCQAnswers;

import java.util.ArrayList;

public interface ISplitMCQSAnswerService {
	public ArrayList<MCQAnswers> splitAnswers(MCQAnswers mcqAnswers);
}
