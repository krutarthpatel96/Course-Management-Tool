package com.group3.AdminAndAuthorization.DAO;

import java.util.ArrayList;

public interface IInstructorHandlerDAO {
	String createNewInstructor(String mailId, String courseId);

	boolean isInstructorExists(String mailId);

	String deleteinstructor(String mailId);

	ArrayList<String> getInstructorCourses(String maildId);
}
