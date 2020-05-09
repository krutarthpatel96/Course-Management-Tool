package com.group3.CreateQuestion.BusinessModels;

import java.util.ArrayList;
import java.util.List;

public class MCQQuestion extends Question {

	private List<String> options = new ArrayList<String>();

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public void addQuestion(String title, String text, String type) {
		super.setQuestionTitle(title);
		super.setQuestionText(text);
		super.setQuestionType(type);
	}

}
