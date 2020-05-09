package com.group3.AdminAndAuthorization;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import com.group3.AdminAndAuthorization.DAO.*;
import com.group3.SignUp.DAO.DAOAbstractFactory;
import com.group3.SignUp.DAO.IUserDAO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.group3.BusinessModels.Course;
import com.group3.BusinessModels.Guest;

import com.group3.AdminAndAuthorization.Services.IServiceAbstractFactory;
import com.group3.AdminAndAuthorization.Services.IViewCoursesService;
import com.group3.AdminAndAuthorization.Services.ServiceAbstractFactory;
import com.group3.groupmanager.GroupmanagerApplication;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {
		AdminDashBoardMainPageController.class, GroupmanagerApplication.class })

class GrantInstructorAccessControllerTest {
	ArrayList<Course> courseList;
	ArrayList<Guest> userlist;
	IUserDAO userDAO;
	IDeleteUserDAO deleteUserDAO;
	IAddCourseDAO addCourseDAO;
	IDeleteCourseDAO deleteCourseDAO;
	@Autowired
	private MockMvc mockMvc;

	public GrantInstructorAccessControllerTest() {
		IDAOAbstractFactory injector = DAOMockAbstractFactory.instance();
		IViewCoursesDAO iViewCoursesDAO = injector.createViewCourseDAO();
		IGrantInstructorAccessDAO iGrantInstructorAccessDAO = injector.createGrantInstructorAccessDAO();
		IServiceAbstractFactory iServiceInjector = ServiceAbstractFactory.instance();
		IViewCoursesService iViewCoursesService = iServiceInjector.createViewCoursesService(iViewCoursesDAO);
		IGrantInstructorAccessService iGrantInsructorAccessService = iServiceInjector
				.createGrantInstructorAccessService(iGrantInstructorAccessDAO);

		courseList = iViewCoursesService.getAllCourses();
		userlist = iGrantInsructorAccessService.returnUserList();
		userDAO = DAOAbstractFactory.instance().createUserDAO();
		deleteUserDAO = DAOMockAbstractFactory.instance().createDeleteUserDAO();
		addCourseDAO = DAOMockAbstractFactory.instance().createAddCourseDAO();
		deleteUserDAO = DAOMockAbstractFactory.instance().createDeleteUserDAO();
	}

	@Test
	final void testGrantInstructorPage() throws Exception {
		this.mockMvc.perform(get("/grantInstructorPage").with(user("user").password("password").roles("ADMIN")))
				.andDo(print()).andExpect(status().isOk()).andExpect(model().attributeExists("courseList"))
				.andExpect(model().attributeExists("userlist"));
	}

	@Test
	final void testGrantInstructorsRoleSwitching() throws Exception {
		String email = "justin@dal.ca";
		String firstName = "Justin";
		String lastName = "Treadue";
		String previousRole = "Guest";
		String expectedRole = "Instructor";
		String pass = "Hello@123";
		String courseId;
		String expectedOutcome;

		Course course;
		Guest guest;

		guest = new Guest();
		guest.setEmail(email);
		guest.setFirstName(firstName);
		guest.setLastName(lastName);
		guest.setUserRole(previousRole);
		guest.setEncryptedPassword(pass);

		course = new Course();
		course.setCourseName("Data Technology");
		course.setCourseId("CSCT5111");

		userDAO.getSignUpDetailsofUser(guest);
		courseId = course.getCourseId() + "-" + course.getCourseName();
		expectedOutcome = firstName + " " + lastName + " " + " switched their role from " + previousRole + " to "
				+ expectedRole;

		this.mockMvc
				.perform(post("/GrantRoleRequest").param("lastName", lastName).param("firstName", firstName)
						.param("email", email).param("role", expectedRole).param("CourseId", courseId)
						.with(user("user").password("password").roles("ADMIN")))
				.andDo(print()).andExpect(status().isOk()).andExpect(model().attributeExists("courseList"))
				.andExpect(model().attributeExists("userlist")).andExpect(model().attributeExists("feedbackMessage"));
		deleteUserDAO.deleteUser(email);
	}
}
