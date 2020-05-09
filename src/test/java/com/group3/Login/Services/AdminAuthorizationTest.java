package com.group3.Login.Services;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.group3.groupmanager.GroupmanagerApplication;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GroupmanagerApplication.class)
public class AdminAuthorizationTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).alwaysDo(print()).build();
	}

	@Test
	public void adminDashboardNotAccessibletoOtherUsers() throws Exception {

		mockMvc.perform(get("/adminMainPageRequest")
				.with(user("user").password("password").roles("GUEST", "STUDENT", "TA", "INSTRUCTOR")))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/accessdenied"));
	}

	@Test
	public void adminLogoutNotAccessibletoOtherUsers() throws Exception {

		mockMvc.perform(get("/adminLogout")
				.with(user("user").password("password").roles("GUEST", "STUDENT", "TA", "INSTRUCTOR")))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/accessdenied"));

	}

	@Test
	public void addCoursePageNotAccessibletoOtherUsers() throws Exception {

		mockMvc.perform(get("/addCoursePageRequest")
				.with(user("user").password("password").roles("GUEST", "STUDENT", "TA", "INSTRUCTOR")))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/accessdenied"));
	}

	@Test
	public void addCourseNotAccessibletoOtherUsers() throws Exception {

		mockMvc.perform(
				get("/addCourse").with(user("user").password("password").roles("GUEST", "STUDENT", "TA", "INSTRUCTOR")))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/accessdenied"));
	}

	@Test
	public void deleteCoursePageNotAccessibletoOtherUsers() throws Exception {

		mockMvc.perform(get("/DeleteCoursePage")
				.with(user("user").password("password").roles("GUEST", "STUDENT", "TA", "INSTRUCTOR")))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/accessdenied"));
	}

	@Test
	public void deleteCourseNotAccessibletoOtherUsers() throws Exception {

		mockMvc.perform(get("/deleteCourse")
				.with(user("user").password("password").roles("GUEST", "STUDENT", "TA", "INSTRUCTOR")))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/accessdenied"));
	}

	@Test
	public void grantInstructorPageNotAccessibletoOtherUsers() throws Exception {

		mockMvc.perform(get("/grantInstructorPage")
				.with(user("user").password("password").roles("GUEST", "STUDENT", "TA", "INSTRUCTOR")))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/accessdenied"));
	}

	@Test
	public void grantRoleNotAccessibletoOtherUsers() throws Exception {

		mockMvc.perform(get("/GrantRoleRequest")
				.with(user("user").password("password").roles("GUEST", "STUDENT", "TA", "INSTRUCTOR")))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/accessdenied"));
	}

	@Test
	public void adminViewCoursesPageNotAccessibletoOtherUsers() throws Exception {

		mockMvc.perform(get("/ViewCoursesPage")
				.with(user("user").password("password").roles("GUEST", "STUDENT", "TA", "INSTRUCTOR")))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/accessdenied"));
	}

}
