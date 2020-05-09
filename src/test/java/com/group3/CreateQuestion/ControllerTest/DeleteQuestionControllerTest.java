package com.group3.CreateQuestion.ControllerTest;

import com.group3.CreateQuestion.DeleteQuestionController;
import com.group3.groupmanager.GroupmanagerApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { DeleteQuestionController.class,
		GroupmanagerApplication.class })
class DeleteQuestionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void deleteQuestionTest() throws Exception {

		this.mockMvc.perform(get("/deleteQuestion").param("questionId", "0")).andDo(print()).andExpect(status().isOk());
	}
}