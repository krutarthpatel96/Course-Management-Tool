package com.group3.Course.DAO;

import java.util.ArrayList;

import com.group3.BusinessModels.Course;

public interface ITADAO {
	public ArrayList<Course> getCoursesByTAMailId(String studentMailId);
}
