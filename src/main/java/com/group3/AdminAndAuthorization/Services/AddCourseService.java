package com.group3.AdminAndAuthorization.Services;

import java.util.ArrayList;

import com.group3.AdminAndAuthorization.Services.IAddCourseService;
import com.group3.AdminAndAuthorization.Services.ICourseInputValidation;
import org.springframework.stereotype.Service;

import com.group3.BusinessModels.Course;
import com.group3.AdminAndAuthorization.DAO.IAddCourseDAO;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddCourseService implements IAddCourseService {
	private IAddCourseDAO addCourseDAO;
	public AddCourseService addCourseService;
	private ArrayList<String> errorList, successMessage;
	private Logger logger = LogManager.getLogger(AddCourseService.class);

	public AddCourseService(IAddCourseDAO addCourseDAO) {
		this.addCourseDAO = addCourseDAO;
		errorList = new ArrayList<>();
		successMessage = new ArrayList<>();
	}

	public ArrayList<String> insertCourseDetails(Course course, ICourseInputValidation icourseInputValidation) {
		String feedBackMessage;

		errorList = icourseInputValidation.validateInputCourse(course);
		if (errorList.size() > 0) {
			return errorList;
		} else {
			feedBackMessage = addCourseDAO.isCourseExist(course.getCourseId());

			if (feedBackMessage.isEmpty()) {
				feedBackMessage = addCourseDAO.addCourse(course);
			}

			successMessage.add(feedBackMessage);
			return successMessage;
		}
	}
}