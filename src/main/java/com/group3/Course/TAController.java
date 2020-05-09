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

import com.group3.BusinessModels.Course;
import com.group3.BusinessModels.Student;

import com.group3.Course.DAO.DAOAbstractFactory;
import com.group3.Course.DAO.IDAOAbstractFactory;
import com.group3.Course.Services.*;

@Controller
public class TAController {
	IServiceAbstractFactory serviceAbstractFactory;
	IDAOAbstractFactory daoInjector;
	ITAManager taManager;
	ICourseManager courseManager;
	Course courseModel;
	Connection conn;
	String sql;
	PreparedStatement statement;

	private static Logger logger = LogManager.getLogger(TAController.class);

	public TAController() {
		daoInjector = DAOAbstractFactory.instance();
		serviceAbstractFactory = ServiceAbstractFactory.instance();
		taManager = serviceAbstractFactory.createTAManager(daoInjector);
		courseManager = serviceAbstractFactory.createCourseManager(daoInjector);
		courseModel = new Course();
	}

	@RequestMapping("/showAllStudents")
	public ModelAndView getAllStudents() {
		courseModel.setCourseId(CourseController.courseId);
		courseModel.setCourseName(CourseController.courseName);
		logger.info("SHOW ALL STUDENTS");

		ArrayList<Student> rows = taManager.getAllStudents();

		ModelAndView mv = new ModelAndView();
		mv.addObject("studentList", rows);
		mv.addObject("courseInfo", courseModel);
		mv.setViewName("studentList.html");
		return mv;
	}

	@RequestMapping("/searchStudent")
	public ModelAndView searchStudent(@RequestParam String studentMailId) {
		logger.info("SEARCH STUDENT: " + studentMailId);

		ArrayList<Student> rows = taManager.getStudentByMailId(studentMailId);
		logger.info(rows);
		ModelAndView mv = new ModelAndView();
		mv.addObject("studentList", rows);
		mv.addObject("courseInfo", courseModel);
		mv.setViewName("studentList.html");
		return mv;
	}

	@RequestMapping("/addTA")
	public ModelAndView addStudentAsTA(@RequestParam String studentMailId) {
		logger.info("ADD TA: " + studentMailId);

		taManager.addTA(studentMailId);

		logger.info("TAship assigned");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String role;
		String email = authentication.getName();
		String formattedRole = null;

		@SuppressWarnings("unchecked")
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) authentication
				.getAuthorities();
		for (GrantedAuthority authority : authorities) {
			role = authority.getAuthority().replace("ROLE_", "").toLowerCase();
			System.out.println("Role formatted: " + role.substring(0, 1).toUpperCase() + role.substring(1));
			formattedRole = role.substring(0, 1).toUpperCase() + role.substring(1);
		}

		role = formattedRole;
		ArrayList<Course> rows = new ArrayList<Course>();

		ModelAndView mv = new ModelAndView();
		if (role.equals("Guest")) {
			rows = courseManager.getCoursesForGuest();
			mv.addObject("courseInfo", rows);
			mv.setViewName("showCoursesGuest.html");
		} else if (role.equals("Instructor")) {
			rows = courseManager.getCoursesByInstructorMailId(email);
			mv.addObject("courseInfo", rows);
			mv.setViewName("showCourses.html");
		} else if (role.equals("TA") || role.equals("Student")) {
			rows = courseManager.getCoursesByTAMailId(email);
			mv.addObject("courseInfo", rows);
			mv.setViewName("showCourses.html");
		}

		return mv;
	}
}