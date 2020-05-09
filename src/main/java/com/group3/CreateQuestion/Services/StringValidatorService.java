package com.group3.CreateQuestion.Services;

import com.group3.CreateQuestion.DAO.IValidationRulesLoaderDAO;

import java.util.ArrayList;

public class StringValidatorService implements IStringValidatorService {
	private ArrayList<IStringValidatorService> enabledValidationServiceList;
	private IValidationRulesLoaderDAO validationRulesLoaderDAO;
	private ValidationRulesLoader validationRulesLoader;

	public StringValidatorService(IValidationRulesLoaderDAO validationRulesLoaderDAO) {

		this.validationRulesLoaderDAO = validationRulesLoaderDAO;
		validationRulesLoader = ValidationRulesLoader.instance(validationRulesLoaderDAO);
	}

	@Override
	public boolean isValid(String inputString) {

		enabledValidationServiceList = validationRulesLoader.returnValidationRules();
		for (IStringValidatorService stringValidatorService : enabledValidationServiceList) {

			if (stringValidatorService.isValid(inputString) == false) {
				return false;
			}
		}
		return true;
	}
}