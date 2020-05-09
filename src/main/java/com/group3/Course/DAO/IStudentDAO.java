package com.group3.Course.DAO;

import java.util.ArrayList;

import com.group3.BusinessModels.Student;

public interface IStudentDAO {
	public ArrayList<Student> getAllStudents();

	public ArrayList<Student> getStudentByMailId(String studentMailId);

	public void assignTA(String studentMailId);
}
