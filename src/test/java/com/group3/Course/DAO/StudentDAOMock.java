package com.group3.Course.DAO;

import com.group3.BusinessModels.Student;

import java.util.ArrayList;

public class StudentDAOMock implements IStudentDAO {

	ArrayList<Student> studentList = null;

	public StudentDAOMock() {

		studentList = new ArrayList<Student> ();

		Student student = new Student();
		student.setFirstName("Anudish");
		student.setLastName("Jain");
		student.setUserRole("Guest");
		student.setEmail("anjain@dal.ca");
		student.setEncryptedPassword("GetMeRight@1234");
		studentList.add(student);

		student = new Student();
		student.setFirstName("Krutarth");
		student.setLastName("Patel");
		student.setUserRole("Student");
		student.setEmail("krutarth07@dal.ca");
		student.setEncryptedPassword("GetMeRightToo@1234");
		studentList.add(student);
	}

	@Override
	public ArrayList<Student> getAllStudents() {

		return studentList;
	}

	@Override
	public ArrayList<Student> getStudentByMailId(String studentMailId) {

		ArrayList<Student> students = new ArrayList<Student> ();

		for (Student studentDetail: studentList) {
			if ((studentDetail.getUserRole().equals("Student") || studentDetail.getUserRole().equals("TA")) && studentDetail.getEmail().contains(studentMailId)) {
				students.add(studentDetail);
			}
		}

		return students;
	}

	@Override
	public void assignTA(String studentMailId) {

		for (Student studentDetail: studentList) {
			if (studentDetail.getEmail().equals(studentMailId)) {
				studentDetail.setUserRole("TA");
			}
		}

	}

}