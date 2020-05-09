package com.group3.Course.Services;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.group3.BusinessModels.Student;

import com.group3.Course.DAO.*;

public class TAManager implements ITAManager {

	IStudentDAO studentDAO;
	IDAOAbstractFactory daoInjector;

	private static Logger logger = LogManager.getLogger(TAManager.class);

	public TAManager(IDAOAbstractFactory daoInjector) {

		studentDAO = daoInjector.createStudentDAO();
	}

	public ArrayList<Student> getAllStudents() {

		ArrayList<Student> rows = studentDAO.getAllStudents();
		return rows;
	}

	public ArrayList<Student> getStudentByMailId(String studentMailId) {

		ArrayList<Student> rows = studentDAO.getStudentByMailId(studentMailId);
		return rows;
	}

	public void addTA(String studentMailId) {

		studentDAO.assignTA(studentMailId);
	}
}