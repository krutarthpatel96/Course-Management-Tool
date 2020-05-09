package com.group3.CreateQuestion.Services;

public interface IMakeQuestionGenerationAbstractFactory {
	public IQuestionService makeQuestion(String questionType);
}
