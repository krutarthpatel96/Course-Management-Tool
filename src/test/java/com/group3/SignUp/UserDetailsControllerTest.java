package com.group3.SignUp;

import com.group3.groupmanager.GroupmanagerApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { UserDetailsController.class,
		GroupmanagerApplication.class })
class UserDetailsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void test() throws Exception {
		this.mockMvc
				.perform(post("/formSubmit").param("lastName", "MyLastName").param("firstName", "MyFirstName")
						.param("email", "TestControl@outlook.com").param("psw", "MyPassword@1234")
						.param("psw-repeat", "MyPassword@1234"))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("")));
	}
}