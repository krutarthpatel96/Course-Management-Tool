package com.group3.Course.DAO;

import com.group3.BusinessModels.Course;
import com.group3.BusinessModels.Instructor;
import com.group3.BusinessModels.Student;

import java.util.ArrayList;
import java.util.HashMap;

public class InstructorDAOMock implements IInstructorDAO {

	ArrayList<Student> list = new ArrayList<Student>();
	ArrayList<Course> courses = new ArrayList<Course>();

	HashMap<String, ArrayList<Course>> courseList = new HashMap<String, ArrayList<Course>>();

	public InstructorDAOMock() {

		Student student;
		Instructor instructor;
		Course course = new Course();
		course.setCourseId("1");
		course.setCourseName("CSCI 1: Learn to build robots");
		courses.add(course);

		student = new Student();
		student.setFirstName("John");
		student.setLastName("Wick");
		student.setUserRole("Student");
		student.setEmail("jwick@dal.ca");
		student.setEncryptedPassword("Ilovedogs@1234");
		list.add(student);
		courseList.put("jwick@dal.ca", courses);

		student = new Student();
		student.setFirstName("Tony");
		student.setLastName("Stark");
		student.setUserRole("TA");
		student.setEmail("tstark@dal.ca");
		student.setEncryptedPassword("Jarvis@1234");
		list.add(student);
		courseList.put("tstark@dal.ca", courses);

		instructor = new Instructor();
		instructor.setFirstName("Steve");
		instructor.setLastName("Rogers");
		instructor.setUserRole("Instructor");
		instructor.setEmail("srogers@dal.ca");
		instructor.setEncryptedPassword("Iamyoung@1234");
		list.add(student);
		courseList.put("srogers@dal.ca", courses);
	}

	@Override
	public ArrayList<Course> getCoursesByInstructorMailId(String instructorMailId) {

		ArrayList<Course> courseTaken = new ArrayList<Course>();
		courseTaken = courseList.get(instructorMailId);
		return courseTaken;
	}
}
