package com.group3.CreateQuestion.DAO;

import com.group3.CreateQuestion.BusinessModels.MCQAnswers;

import java.util.ArrayList;

public interface ISaveMCQAnswerstoDataBaseDAO {
	int saveOptionsToDataBase(int id, ArrayList<MCQAnswers> mcqAnswers);
}
