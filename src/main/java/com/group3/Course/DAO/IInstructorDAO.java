package com.group3.Course.DAO;

import java.util.ArrayList;

import com.group3.BusinessModels.Course;

public interface IInstructorDAO {
	public ArrayList<Course> getCoursesByInstructorMailId(String instructorMailId);
}
