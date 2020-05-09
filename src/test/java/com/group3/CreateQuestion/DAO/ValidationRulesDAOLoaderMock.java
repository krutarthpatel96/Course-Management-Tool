package com.group3.CreateQuestion.DAO;

import java.util.ArrayList;

public class ValidationRulesDAOLoaderMock implements IValidationRulesLoaderDAO {
	private ArrayList<String> validationRulesList;
	private String rule;

	public ValidationRulesDAOLoaderMock() {

		validationRulesList = new ArrayList<>();
	}

	@Override
	public ArrayList<String> getValidationRules() {

		rule = new String("CHECK_EMPTY_STRING");
		validationRulesList.add(rule);

		rule = new String("CHECK_START_NUMERIC");
		validationRulesList.add(rule);

		rule = new String("CHECK_START_SPECIAL_CHAR");
		validationRulesList.add(rule);

		return validationRulesList;
	}
}