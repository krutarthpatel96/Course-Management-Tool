package com.group3.CreateQuestion.BusinessModels;

public class NumericQuestion extends Question {

	public void addQuestion(String title, String text, String type) {
		super.setQuestionTitle(title);
		super.setQuestionText(text);
		super.setQuestionType(type);
	}

}
