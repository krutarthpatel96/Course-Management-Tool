package com.group3.Course.Services;

import java.util.ArrayList;

import com.group3.BusinessModels.Course;

public interface ICourseManager {
	public ArrayList<Course> getCoursesByTAMailId(String studentMailId);

	public ArrayList<Course> getCoursesByInstructorMailId(String instructorMailId);

	public ArrayList<Course> getCoursesForGuest();
}
