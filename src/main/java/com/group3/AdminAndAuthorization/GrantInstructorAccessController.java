package com.group3.AdminAndAuthorization;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group3.AdminAndAuthorization.DAO.*;
import com.group3.AdminAndAuthorization.Services.*;
import com.group3.BusinessModels.Course;
import com.group3.BusinessModels.Guest;

@Controller
public class GrantInstructorAccessController {
	IDAOAbstractFactory daoInjector;
	IViewCoursesDAO viewCoursesDAO;
	IGrantInstructorAccessDAO grantInstructorAccessDAO;
	IServiceAbstractFactory serviceInjector;
	IViewCoursesService viewCoursesService;
	IGrantInstructorAccessService grantInsructorAccessService;
	IGrantAccessFieldsValidation grantAccessFieldsValidation;
	IInstructorHandlerDAO instructorHandlerDAO;
	IUserRoleHandlerDAO userRoleHandlerDAO;
	IExtractCourseIdService extractCourseIdService;

	ArrayList<Course> courseList;
	ArrayList<Guest> userlist;

	public GrantInstructorAccessController() {
		daoInjector = DAOAbstractFactory.instance();
		viewCoursesDAO = daoInjector.createViewCourseDAO();
		grantInstructorAccessDAO = daoInjector.createGrantInstructorAccessDAO();
		serviceInjector = ServiceAbstractFactory.instance();
		viewCoursesService = serviceInjector.createViewCoursesService(viewCoursesDAO);
		grantInsructorAccessService = serviceInjector.createGrantInstructorAccessService(grantInstructorAccessDAO);
		courseList = viewCoursesService.getAllCourses();
		userlist = grantInsructorAccessService.returnUserList();
	}

	@RequestMapping("/grantInstructorPage")
	public String grantInstructorPage(Model model) {
		model.addAttribute("userlist", userlist);
		model.addAttribute("courseList", courseList);
		return "GrantAccessPage.html";

	}

	@RequestMapping("/GrantRoleRequest")
	public String grantInstructorRole(Guest guestmodel, @RequestParam("CourseId") String courseId, String role,
			Model model) {
		String feebackMessage;
		guestmodel.setUserRole(role);
		System.out.println(guestmodel.getUserRole());

		instructorHandlerDAO = daoInjector.createInstructorHandlerDAO();
		userRoleHandlerDAO = daoInjector.createUserRoleHandlerDAO();
		grantAccessFieldsValidation = serviceInjector.createGrantAccessFieldsValidation(courseId, role);

		String validationMessage = grantAccessFieldsValidation.validateFields();
		if (validationMessage.length() > 0) {
			model.addAttribute("feedbackMessage", validationMessage);
			model.addAttribute("userlist", userlist);
			model.addAttribute("courseList", courseList);
			return "GrantAccessPage.html";
		}

		extractCourseIdService = serviceInjector.createExtractCourseIdService(courseId);
		courseId = extractCourseIdService.extractCourseId();

		feebackMessage = serviceInjector
				.createAdminPageServices(instructorHandlerDAO, userRoleHandlerDAO, guestmodel, courseId)
				.alterUserRole();
		System.out.println(feebackMessage);

		model.addAttribute("feedbackMessage", feebackMessage);
		model.addAttribute("userlist", userlist);
		model.addAttribute("courseList", courseList);
		return "GrantAccessPage.html";
	}
}