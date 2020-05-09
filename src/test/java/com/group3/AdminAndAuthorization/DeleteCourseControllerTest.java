package com.group3.AdminAndAuthorization;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import com.group3.AdminAndAuthorization.DAO.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.group3.BusinessModels.Course;
import com.group3.AdminAndAuthorization.Services.IViewCoursesService;
import com.group3.AdminAndAuthorization.Services.ServiceAbstractFactory;
import com.group3.groupmanager.GroupmanagerApplication;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {
		AdminDashBoardMainPageController.class, GroupmanagerApplication.class })

class DeleteCourseControllerTest {
	@Autowired
	private MockMvc mockMvc;

	ArrayList<Course> courseList;
	Course course;
	String CourseId, CourseName;

	private IAddCourseDAO iAddcourseDAO;
	private IDeleteCourseDAO iDeleteCourseDAO;

	public DeleteCourseControllerTest() {
		IDAOAbstractFactory injector = DAOAbstractFactory.instance();
		iDeleteCourseDAO = injector.createDeleteCourseDAO();
		iAddcourseDAO = injector.createAddCourseDAO();
		course = new Course();
		course.setCourseId("CSCT3100");
		course.setCourseName("NLP Advanced");
	}

	@Test
	final void testDeleteCoursePage() throws Exception {
		iAddcourseDAO.addCourse(course);
		this.mockMvc.perform(get("/DeleteCoursePage").with(user("user").password("passwrd").roles("ADMIN")))
				.andDo(print()).andExpect(status().isOk());
		iDeleteCourseDAO.deleteCourse(course);
	}

	@Test
	final void testDeleteCourseRequest() throws Exception {

		iAddcourseDAO.addCourse(course);
		String CourseId = course.getCourseId();
		String CourseName = course.getCourseName();
		String expectedMessage = course.getCourseName() + " (" + course.getCourseId() + ") "
				+ " is deleted sucessfully ";
		this.mockMvc
				.perform(post("/deleteCourse").param("CourseId", CourseId).param("CourseName", CourseName)
						.with(user("user").password("password").roles("ADMIN")))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(model().attribute("feedBackMessage", expectedMessage));
		iAddcourseDAO.addCourse(course);
	}
}
