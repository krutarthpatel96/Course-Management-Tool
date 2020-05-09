package com.group3.Course.DAO;

import com.group3.BusinessModels.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentDAOTest {

	IDAOAbstractFactory daoInjector;
	IStudentDAO studentDAO;
	InsertTestDataIntoDB insert;
	RemoveTestDataFromDB remove;

	public StudentDAOTest() {
		daoInjector = DAOAbstractFactory.instance();
		studentDAO = daoInjector.createStudentDAO();
		insert = new InsertTestDataIntoDB();
		remove = new RemoveTestDataFromDB();
	}

	@Test
	void getAllStudentsTest() {

		ArrayList<Student> studentList = new ArrayList<Student>();
		studentList = studentDAO.getAllStudents();
		assertNotNull(studentList);
		assertThat(studentList).isNotEmpty();
		assertTrue(studentList.size() > 0);
	}

	@Test
	public void getStudentByMailIdTest() throws Exception {

		ArrayList<Student> studentList;
		Student student = new Student();
		student.setLastName("Wick");
		student.setFirstName("John");
		student.setEmail("test@dal.ca");
		insert.StudentDAOTest_insertStudent(student);

		studentList = studentDAO.getStudentByMailId("test@dal.ca");
		assertNotNull(studentList);
		assertThat(studentList).isNotEmpty();
		assertEquals(studentList.size(), 1);

		remove.StudentDAOTest_removeStudent(student);
	}

	@Test
	public void assignTA() throws Exception {

		ArrayList<Student> studentList;
		Student student = new Student();
		student.setLastName("Wick");
		student.setFirstName("John");
		student.setEmail("test2@dal.ca");
		insert.StudentDAOTest_insertStudent(student);
		insert.StudentDAOTest_updateTA(student);

		studentList = new ArrayList<Student>();
		studentDAO.assignTA("test2@dal.ca");
		studentList = studentDAO.getStudentByMailId("test2@dal.ca");
		assertNotNull(studentList);
		assertThat(studentList).isNotEmpty();
		assertEquals(studentList.size(), 1);
		assertEquals(studentList.get(0).getUserRole(), "TA");

		remove.StudentDAOTest_removeStudent(student);
	}

}