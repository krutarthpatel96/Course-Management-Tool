package com.group3.Course.Services;

import java.util.ArrayList;

import com.group3.BusinessModels.Student;

public interface ITAManager {
	public ArrayList<Student> getAllStudents();

	public ArrayList<Student> getStudentByMailId(String studentMailId);

	public void addTA(String studentMailId);
}
