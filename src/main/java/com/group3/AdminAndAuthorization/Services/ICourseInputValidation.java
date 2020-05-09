package com.group3.AdminAndAuthorization.Services;

import java.util.ArrayList;

import com.group3.BusinessModels.Course;

public interface ICourseInputValidation {
	ArrayList<String> validateInputCourse(Course course);
}