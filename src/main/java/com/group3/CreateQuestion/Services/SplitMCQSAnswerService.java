package com.group3.CreateQuestion.Services;

import com.group3.CreateQuestion.BusinessModels.MCQAnswers;

import java.util.ArrayList;

public class SplitMCQSAnswerService implements ISplitMCQSAnswerService {

	private ArrayList<MCQAnswers> mcqAnswersList;
	private static SplitMCQSAnswerService splitMCQSAnswerService;

	public SplitMCQSAnswerService() {

		mcqAnswersList = new ArrayList<>();
	}

	@Override
	public ArrayList<MCQAnswers> splitAnswers(MCQAnswers mcqAnswers) {

		mcqAnswersList = new ArrayList<>();
		String answerText = mcqAnswers.getAnswer();
		String storedAs = mcqAnswers.getStoredAs();
		String[] answerTextList = answerText.split(",");
		String[] storedAsList = storedAs.split(",");

		for (int i = 0; i < answerTextList.length; ++i) {
			MCQAnswers mcqAnswersInstance = new MCQAnswers();
			mcqAnswersInstance.setAnswer(answerTextList[i]);
			mcqAnswersInstance.setStoredAs(storedAsList[i]);
			mcqAnswersList.add(mcqAnswersInstance);
		}
		return mcqAnswersList;
	}
}
