package com.group3.CreateQuestion.BusinessModels;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MCQQuestionsTest {

	MCQQuestion mcq;
	List<String> options;

	@BeforeEach
	public void init() {

		mcq = new MCQQuestion();
		options = new ArrayList<String>();
	}

	@Test
	void getOptionsTest() {

		options = mcq.getOptions();
		assertThat(options).isEmpty();
	}

	@Test
	void setOptionsTest() {

		options.add("1");
		mcq.setOptions(options);

		assertNotNull(mcq.getOptions());
		assertTrue(mcq.getOptions().size() == 1);
	}

	@Test
	void addQuestionTest() {

		mcq.addQuestion("Test Title", "Test Text", "Test Type");
		options.add("Test Option 1");
		options.add("Test Option 2");
		mcq.setOptions(options);

		assertTrue(mcq.getQuestionTitle().equals("Test Title"));
		assertTrue(mcq.getQuestionText().equals("Test Text"));
		assertTrue(mcq.getQuestionType().equals("Test Type"));
		assertTrue(mcq.getOptions().size() == 2);
	}

}