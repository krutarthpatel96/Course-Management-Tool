package com.group3.CreateQuestion.ControllerTest;

import com.group3.CreateQuestion.RetrieveQuestionController;
import com.group3.groupmanager.GroupmanagerApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {
		RetrieveQuestionController.class, GroupmanagerApplication.class })
class RetrieveQuestionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void deleteQuestionTest() throws Exception {

		this.mockMvc.perform(get("/showAvailableQuestionsToDelete").param("questionId", "0"))
				.andExpect(model().attributeExists("questionList"));
	}

	@Test
	public void showQuestionSortedTest() throws Exception {

		this.mockMvc.perform(get("/showAvailableQuestionsSorted").param("order", "TITLE ASC").param("mode", "visible"))
				.andExpect(model().attributeExists("questionList")).andExpect(model().attributeExists("deleteQuery"));
	}
}