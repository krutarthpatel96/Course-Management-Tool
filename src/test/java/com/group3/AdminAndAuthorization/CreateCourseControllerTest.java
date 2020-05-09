package com.group3.AdminAndAuthorization;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import com.group3.AdminAndAuthorization.DAO.IAddCourseDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.group3.BusinessModels.Course;
import com.group3.AdminAndAuthorization.DAO.DAOAbstractFactory;
import com.group3.AdminAndAuthorization.DAO.IDeleteCourseDAO;
import com.group3.groupmanager.GroupmanagerApplication;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {
		AdminDashBoardMainPageController.class, GroupmanagerApplication.class })
class CreateCourseControllerTest {
	@Autowired
	private MockMvc mockMvc;
	private IDeleteCourseDAO deleteCourseDAO;
	private Course course;

	public CreateCourseControllerTest() {

		deleteCourseDAO = DAOAbstractFactory.instance().createDeleteCourseDAO();
		course = new Course();
		course.setCourseId("csci5608");
		course.setCourseName("Solid Mechanics");
		deleteCourseDAO.deleteCourse(course);
	}

	@Test
	final void testRenderCoursePage() throws Exception {

		this.mockMvc.perform(get("/addCoursePageRequest").with(user("user").password("password").roles("ADMIN")))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("")));
	}

	@Test
	final void testAddCourseCreatedSucess() throws Exception {

		ArrayList<String> operationFeedback = new ArrayList<>();
		operationFeedback.add("Solid Mechanics with CSCI5608 created successfully");
		this.mockMvc
				.perform(post("/addCourse").param("CourseId", "CSCI5608").param("CourseName", "Solid Mechanics")
						.with(user("user").password("password").roles("ADMIN")))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(model().attribute("operationFeedback", operationFeedback));

		operationFeedback.clear();
		course = new Course();
		course.setCourseId("csci5608");
		course.setCourseName("Solid Mechanics");
		deleteCourseDAO.deleteCourse(course);
	}

	@Test
	final void testAddCourseAlreadyExist() throws Exception {
		IAddCourseDAO addCourseDAO;
		IDeleteCourseDAO deleteCourseDAO;
		ArrayList<String> operationFeedback = new ArrayList<>();
		Course course = new Course();
		course.setCourseId("csci7000");
		course.setCourseName("Visual Processing");

		addCourseDAO = DAOAbstractFactory.instance().createAddCourseDAO();
		addCourseDAO.addCourse(course);

		operationFeedback.add("Course Name  Visual Processing with Course ID csci7000 already exists !! ");
		this.mockMvc
				.perform(post("/addCourse").param("CourseId", "csci7000").param("CourseName", "Visual Processing")
						.with(user("user").password("password").roles("ADMIN")))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(model().attribute("operationFeedback", operationFeedback));

		deleteCourseDAO = DAOAbstractFactory.instance().createDeleteCourseDAO();
		deleteCourseDAO.deleteCourse(course);
	}
}