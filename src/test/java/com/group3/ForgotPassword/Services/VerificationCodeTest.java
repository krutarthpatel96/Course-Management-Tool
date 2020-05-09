package com.group3.ForgotPassword.Services;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class VerificationCodeTest {

	IVerificationCode verificationCode;

	public VerificationCodeTest() {

		verificationCode = new VerificationCode();
	}

	@Test
	void generateNewCodeTest() {

		String code = verificationCode.getNewCode(5);
		assertThat(code).isNotNull();
		assertThat(code).isNotEmpty();
		assertEquals(code.length(), 5);
	}
}