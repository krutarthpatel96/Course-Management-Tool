package com.group3.AdminAndAuthorization;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group3.BusinessModels.Course;
import com.group3.AdminAndAuthorization.DAO.DAOAbstractFactory;
import com.group3.AdminAndAuthorization.DAO.IDAOAbstractFactory;
import com.group3.AdminAndAuthorization.DAO.IDeleteCourseDAO;
import com.group3.AdminAndAuthorization.DAO.IViewCoursesDAO;
import com.group3.AdminAndAuthorization.Services.IDeleteCourseService;
import com.group3.AdminAndAuthorization.Services.IViewCoursesService;
import com.group3.AdminAndAuthorization.Services.ServiceAbstractFactory;

@Controller
public class DeleteCourseController {
	IDAOAbstractFactory daoInjector;
	IViewCoursesDAO viewCoursesDAO;
	IViewCoursesService viewCoursesService;
	ArrayList<Course> courseList;

	@RequestMapping("/DeleteCoursePage")
	public String deleteCoursePage(Model model) {
		daoInjector = DAOAbstractFactory.instance();
		viewCoursesDAO = daoInjector.createViewCourseDAO();
		viewCoursesService = ServiceAbstractFactory.instance().createViewCoursesService(viewCoursesDAO);
		courseList = viewCoursesService.getAllCourses();

		model.addAttribute("courseList", courseList);
		return "DeleteCoursePage.html";
	}

	@RequestMapping("/deleteCourse")
	public String deleteCourseRequest(Course course, Model model) {
		IDeleteCourseDAO deleteCourseDAO = daoInjector.createDeleteCourseDAO();
		IDeleteCourseService deleteCourseService = ServiceAbstractFactory.instance().createDeleteCourseService(deleteCourseDAO,
				course);
		ArrayList<Course> courseList;
		String feedBackMessage;

		viewCoursesDAO = daoInjector.createViewCourseDAO();
		feedBackMessage = deleteCourseService.deleteCourse();
		viewCoursesService = ServiceAbstractFactory.instance().createViewCoursesService(viewCoursesDAO);
		courseList = viewCoursesService.getAllCourses();

		model.addAttribute("feedBackMessage", feedBackMessage);
		model.addAttribute("courseList", courseList);
		return "DeleteCoursePage.html";
	}
}