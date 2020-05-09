package com.group3.CreateQuestion.DAO;

public interface ISaveBasicQuestionInformationDAO {
	String saveDetailsAndReturnId(String title, String text, String type);
}
