package com.group3.AdminAndAuthorization.Services;

import java.util.ArrayList;
import java.util.regex.Pattern;

import com.group3.AdminAndAuthorization.Services.ICourseInputValidation;
import com.group3.BusinessModels.Course;

public class CourseInputValidation implements ICourseInputValidation {
	ArrayList<String> ErrorarrayList = null;
	String courseIdPattern;
	String courseNamePattern;
	String courseId;
	String message;
	String courseName;

	@Override
	public ArrayList<String> validateInputCourse(Course course) {
		ErrorarrayList = new ArrayList<>();
		courseIdPattern = "[A-za-z]{4}[0-9]{4}";
		courseNamePattern = "[A-Za-z{\\s}*]+[0-9]*";
		courseId = course.getCourseId();

		if (Pattern.matches(courseIdPattern, courseId) == false) {
			message = "Invalid Course Id (It Should Like : csci5308)";

			if (ErrorarrayList == null) {
				ErrorarrayList = new ArrayList<>();
			}

			ErrorarrayList.add(message);
		}

		courseName = course.getCourseName();
		if (Pattern.matches(courseNamePattern, courseName) == false) {
			message = "Invalid Course Name (It Should start with String only)";

			if (ErrorarrayList == null) {
				ErrorarrayList = new ArrayList<>();
			}

			ErrorarrayList.add(message);
		}

		return ErrorarrayList;
	}
}