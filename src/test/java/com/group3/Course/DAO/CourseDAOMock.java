package com.group3.Course.DAO;

import com.group3.BusinessModels.Course;
import com.group3.BusinessModels.Instructor;
import com.group3.BusinessModels.Student;

import java.util.ArrayList;
import java.util.HashMap;

public class CourseDAOMock implements ICourseDAO {

	ArrayList<Student> list = new ArrayList<Student>();
	ArrayList<Course> courses = new ArrayList<Course>();

	HashMap<String, ArrayList<Course>> courseList = new HashMap<String, ArrayList<Course>>();
	Course course;

	public CourseDAOMock() {

		Student student;
		Instructor instructor;

		course = new Course();
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
	public ArrayList<String> getEnrolledStudentsByCourseId(String courseId) {
		ArrayList<String> mailId = new ArrayList<String>();
		ArrayList<String> keysHashMap = new ArrayList<String>(courseList.keySet());
		ArrayList<Course> courseTaken;

		for (String studentMailId : keysHashMap) {
			courseTaken = new ArrayList<Course>(courseList.get(studentMailId));
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getEmail().equals(studentMailId) && list.get(i).getUserRole().equals("Student")) {
					for (int j = 0; j < courseTaken.size(); j++) {
						if (courseTaken.get(j).getCourseId().equals(courseId)) {
							mailId.add(studentMailId);
							break;
						}
					}
				}
			}

		}
		return mailId;
	}

	@Override
	public void enrollStudentToCourse(Student studentDetails, String courseId) {

		ArrayList<String> keysHashMap;
		ArrayList<Course> courseTaken;

		Course courseTwo = new Course();
		courseTwo.setCourseId(courseId);
		courseTwo.setCourseName("CSCI 2: Learn to use the shield");
		keysHashMap = new ArrayList<String>(courseList.keySet());

		courseTaken = new ArrayList<Course>(courseList.get(studentDetails.getEmail()));
		courseTaken.add(courseTwo);
		courseList.put(studentDetails.getEmail(), courseTaken);
	}

	@Override
	public ArrayList<Course> getCoursesForGuest() {

		ArrayList<Course> courseTaken = new ArrayList<Course>();
		courseTaken.add(course);
		return courseTaken;
	}
}