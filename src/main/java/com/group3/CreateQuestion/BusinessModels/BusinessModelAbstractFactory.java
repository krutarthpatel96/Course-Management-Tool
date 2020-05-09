package com.group3.CreateQuestion.BusinessModels;

public class BusinessModelAbstractFactory implements IBusinessModelAbstractFactory {
	private static BusinessModelAbstractFactory businessModelAbstractFactory;

	private BusinessModelAbstractFactory() {
	}

	public static BusinessModelAbstractFactory instance() {
		if (null == businessModelAbstractFactory) {
			businessModelAbstractFactory = new BusinessModelAbstractFactory();
		}
		return businessModelAbstractFactory;
	}

	@Override
	public MCQAnswers createMCQSAnswers() {
		return new MCQAnswers();
	}
}
