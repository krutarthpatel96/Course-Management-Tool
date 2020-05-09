package com.group3.Course.DAO;

import java.util.ArrayList;

import com.group3.BusinessModels.Course;
import com.group3.BusinessModels.Student;

public interface ICourseDAO {
	public ArrayList<String> getEnrolledStudentsByCourseId(String courseId);

	public void enrollStudentToCourse(Student studentDetails, String courseId);

	public ArrayList<Course> getCoursesForGuest();
}