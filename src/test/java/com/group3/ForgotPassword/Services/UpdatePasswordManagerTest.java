package com.group3.ForgotPassword.Services;

import com.group3.ForgotPassword.DAO.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdatePasswordManagerTest {

	IDAOAbstractFactory daoInjector;
	IUserPasswordDAO userPasswordDAO;
	IVerificationCode verificationCodeMock;

	public UpdatePasswordManagerTest() {

		daoInjector = DAOMockAbstractFactory.instance();
		userPasswordDAO = daoInjector.getUserDAOObj();
		verificationCodeMock = new VerificationCodeMock();
	}

	@Test
	public void compareCode() {

		String generated_code = new String();
		generated_code = verificationCodeMock.getNewCode(5);
		assertEquals("TCode", generated_code);
	}

	@Test
	public void updatePassword() {

		userPasswordDAO.updateNewPassword("tstark@dal.ca", "hello@123");
		String password = UserPasswordDAOMock.model.getEncryptedPassword();
		assertThat(password).isNotNull();
		assertThat(password).isNotEmpty();
		assertEquals("hello@123", password);
	}
}