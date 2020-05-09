package com.group3.Login.Services;

import com.group3.groupmanager.GroupmanagerApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { LoginTest.class,
		GroupmanagerApplication.class })
public class LoginTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).alwaysDo(print()).build();
	}

	@Test
	public void loginPageShowingForAllUser() throws Exception {
		mockMvc.perform(get("/login")).andExpect(status().isOk());
	}

	@Test
	public void adminCanLoginandLogout() throws Exception {
		mockMvc.perform(formLogin().userParameter("email").user("admin@dal.ca").password("admin"))
				.andExpect(status().isFound()).andExpect(redirectedUrl("adminMainPageRequest"))
				.andExpect(authenticated().withUsername("admin@dal.ca"));

		mockMvc.perform(logout()).andExpect(status().isFound()).andExpect(redirectedUrl("/login?logout"));
	}

	@Test
	public void invalidCredentialsShowingError() throws Exception {
		String loginErrorUrl = "/login?error";
		mockMvc.perform(formLogin().password("invalid")).andExpect(status().isFound())
				.andExpect(redirectedUrl(loginErrorUrl)).andExpect(unauthenticated());

		mockMvc.perform(get(loginErrorUrl))
				.andExpect(content().string(containsString("Invalid username or password.")));
	}

}
