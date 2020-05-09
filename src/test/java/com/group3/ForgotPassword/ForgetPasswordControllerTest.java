package com.group3.ForgotPassword;

import com.group3.groupmanager.GroupmanagerApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {
	ForgetPasswordController.class, GroupmanagerApplication.class
})
class forgetPasswordControllerTest {

	private static Logger logger = LogManager.getLogger(ForgetPasswordController.class);

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void enterEmailTest() {
		try {
			this.mockMvc.perform(get("/enterEmail")).andDo(print()).andExpect(status().isOk());
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Test
	public void enterCode() {
		try {
			this.mockMvc.perform(post("/enterCode").param("email", "falgun@dal.ca")).andDo(print()).andExpect(status().isOk())
				.andExpect(model().attributeExists("status"));
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Test
	public void checkCode() {
		try {
			this.mockMvc.perform(post("/checkCode").param("code_input", "TmpCode")).andDo(print()).andExpect(status().isOk())
				.andExpect(model().attributeExists("invalidcode"));
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Test
	public void passwordUpdator() {
		try {
			this.mockMvc.perform(post("/checkCode").param("password", "tmppassword")).andDo(print()).andExpect(status().isOk());
		} catch (Exception e) {
			logger.error(e);
		}
	}
}