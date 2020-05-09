package com.group3.AdminAndAuthorization.DAO;

import java.util.ArrayList;

import com.group3.BusinessModels.Course;

public interface IViewCoursesDAO {
	ArrayList<Course> getAllCourses();
}