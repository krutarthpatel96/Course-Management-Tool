package com.group3.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.group3.Login.DAO.ILoginDAO;
import com.group3.Course.Services.ICourseManager;
import com.group3.Course.Services.IServiceAbstractFactory;
import com.group3.Course.Services.ServiceAbstractFactory;

import com.group3.BusinessModels.Course;

import com.group3.Course.DAO.DAOAbstractFactory;
import com.group3.Course.DAO.IDAOAbstractFactory;

@Controller
public class CourseController {
	public static String courseId = "NA", courseName = "NA";
	Course courseModel;
	String role = new String();

	private static Logger logger = LogManager.getLogger(CourseController.class);
	Connection conn;
	PreparedStatement statement;

	ILoginDAO loginDAO;
	IDAOAbstractFactory daoInjector;
	com.group3.Login.DAO.IDAOAbstractFactory loginDAOInjector;
	IServiceAbstractFactory serviceAbstractFactory;
	ICourseManager courseManager;

	public CourseController() {

		daoInjector = DAOAbstractFactory.instance();
		serviceAbstractFactory = ServiceAbstractFactory.instance();
		loginDAOInjector = com.group3.Login.DAO.DAOAbstractFactory.instance();
		courseManager = serviceAbstractFactory.createCourseManager(daoInjector);
		loginDAO = loginDAOInjector.createLoginDAO();
	}

	@RequestMapping("/courseAdmin")
	public ModelAndView getCoursesByEmailId() {

		logger.info("COURSE");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		String formattedRole = null;

		@SuppressWarnings("unchecked")
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) authentication
				.getAuthorities();
		for (GrantedAuthority authority : authorities) {
			String role = authority.getAuthority().replace("ROLE_", "").toLowerCase();
			System.out.println("Role formatted: " + role.substring(0, 1).toUpperCase() + role.substring(1));
			formattedRole = role.substring(0, 1).toUpperCase() + role.substring(1);
		}

		role = formattedRole;
		ArrayList<Course> rows = new ArrayList<Course>();

		ModelAndView mv = new ModelAndView();
		if (role.equals("Guest")) {
			rows = courseManager.getCoursesForGuest();
			mv.addObject("courseInfo", rows);
			mv.addObject("questionManager", "hidden");
			mv.setViewName("showCoursesGuest.html");
		} else if (role.equals("Instructor")) {
			rows = courseManager.getCoursesByInstructorMailId(email);
			mv.addObject("courseInfo", rows);
			mv.addObject("questionManager", "visible");
			mv.setViewName("showCourses.html");
		} else if (role.equals("Ta") || role.equals("Student")) {
			rows = courseManager.getCoursesByTAMailId(email);
			mv.addObject("courseInfo", rows);
			mv.addObject("questionManager", "hidden");
			mv.setViewName("showCourses.html");
		}
		return mv;
	};

	@RequestMapping("/selectCourse")
	public ModelAndView getSelectedCourse(@RequestParam String courseId, @RequestParam String courseName) {

		courseModel = new Course();
		courseModel.setCourseId(courseId);
		courseModel.setCourseName(courseName);
		this.courseId = courseId;
		this.courseName = courseName;
		logger.info("SELECT COURSE: " + courseId);

		ModelAndView mv = new ModelAndView();
		if (role.equals("Student")) {
			logger.info("Student Logged In");
			courseModel = new Course();
			courseModel.setCourseId(this.courseId);
			courseModel.setCourseName(this.courseName);
			mv.addObject("courseInfo", courseModel);
			mv.setViewName("course.html");

		} else if (role.equals("Instructor") || role.equals("Ta")) {
			mv.addObject("courseInfo", courseModel);
			mv.setViewName("courseAction.html");

		}

		return mv;
	}

	@RequestMapping("/course")
	public ModelAndView displayCoursePage() {

		ModelAndView mv = new ModelAndView();
		courseModel = new Course();
		courseModel.setCourseId(this.courseId);
		courseModel.setCourseName(this.courseName);
		mv.addObject("courseInfo", courseModel);
		mv.setViewName("course.html");
		return mv;
	}

	@RequestMapping("/showGuestcourse")
	public ModelAndView displayGuestCoursePage() {

		ModelAndView mv = new ModelAndView();
		courseModel = new Course();
		courseModel.setCourseId(this.courseId);
		courseModel.setCourseName(this.courseName);
		mv.addObject("courseInfo", courseModel);
		mv.setViewName("showCoursesGuest.html");
		return mv;
	}
}