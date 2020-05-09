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
public class UsersAuthorizationTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).alwaysDo(print()).build();
	}

	@Test
	public void selectCourseNotAccessibletoGuest() throws Exception {

		mockMvc.perform(get("/selectCourse").with(user("user").password("password").roles("GUEST")))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/accessdenied"));
	}

	@Test
	public void importCSVNotAccessibletoOtherUsers() throws Exception {

		mockMvc.perform(get("/importCSV").with(user("user").password("password").roles("GUEST", "STUDENT")))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/accessdenied"));
	}

	@Test
	public void uploadCSVFileNotAccessibletoOtherUsers() throws Exception {

		mockMvc.perform(get("/upload-csv-file").with(user("user").password("password").roles("GUEST", "STUDENT")))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/accessdenied"));
	}

	@Test
	public void showAllStudentsNotAccessibletoOtherUsers() throws Exception {

		mockMvc.perform(get("/showAllStudents").with(user("user").password("password").roles("GUEST", "STUDENT")))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/accessdenied"));
	}

	@Test
	public void searchStudentNotAccessibletoOtherUsers() throws Exception {

		mockMvc.perform(get("/searchStudent").with(user("user").password("password").roles("GUEST", "STUDENT")))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/accessdenied"));
	}

	@Test
	public void addTaNotAccessibletoOtherUsers() throws Exception {

		mockMvc.perform(get("/addTA").with(user("user").password("password").roles("GUEST", "STUDENT")))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/accessdenied"));
	}

}
