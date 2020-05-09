package com.group3.CreateQuestion.Services;

public class MakeQuestionGenerationAbstractFactory implements IMakeQuestionGenerationAbstractFactory {

	@Override
	public IQuestionService makeQuestion(String questionType) {
		IQuestionService questionService = null;

		switch (questionType) {
		case "Free Text":
			questionService = new FreeTextQuestionGenerationService();
			break;

		case "Numeric":
			questionService = new NumericQuestionGenerationService();
			break;

		case "Multiple Choice, Choose One":
			questionService = new SaveMCQAnswerstoDataBaseService();
			break;

		case "Multiple Choice, Choose Multiple":
			questionService = new SaveMCQAnswerstoDataBaseService();
			break;
		}
		return questionService;
	}
}
