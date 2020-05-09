package com.group3.Course.Services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordGeneratorTest {

	public static PasswordGenerator passwordGenerator;

	public PasswordGeneratorTest() {
		passwordGenerator = new PasswordGenerator();
	}

	@Test
	public void getNewPasswordTest() {

		assertThat(passwordGenerator.getNewPassword(10)).isNotEmpty();
		assertThat(passwordGenerator.getNewPassword(10)).isNotNull();
		assertEquals(passwordGenerator.getNewPassword(10).toString().length(), 10);
	}

}