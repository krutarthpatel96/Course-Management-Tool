package com.group3.CreateQuestion.BusinessModels;

import com.group3.CreateQuestion.DAO.ISaveMCQAnswerstoDataBaseDAO;
import com.group3.CreateQuestion.Services.ISplitMCQSAnswerService;
import com.group3.CreateQuestion.Services.SaveMCQAnswerstoDataBaseService;

import java.util.ArrayList;

public class MCQAnswers {
	private String answer, storedAs;

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getStoredAs() {
		return storedAs;
	}

	public void setStoredAs(String storedAs) {
		this.storedAs = storedAs;
	}

	public ArrayList<MCQAnswers> splitAnswerSevice(ISplitMCQSAnswerService iSplitMCQSAnswerService,
			MCQAnswers mcqAnswers) {
		return iSplitMCQSAnswerService.splitAnswers(mcqAnswers);
	}

	public int saveAnswerstoDatabase(int questionId, ArrayList<MCQAnswers> answersArrayList,
			ISaveMCQAnswerstoDataBaseDAO iSaveMCQAnswerstoDataBaseDAO,
			SaveMCQAnswerstoDataBaseService saveMCQAnswerstoDataBaseService) {
		int success = saveMCQAnswerstoDataBaseService.saveMCQAnswertoDataBase(iSaveMCQAnswerstoDataBaseDAO, questionId,
				answersArrayList);
		return success;
	}
}
