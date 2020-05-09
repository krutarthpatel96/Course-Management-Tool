package com.group3.ForgotPassword.Services;

import com.group3.BusinessModels.Guest;
import com.group3.ForgotPassword.DAO.DAOMockAbstractFactory;
import com.group3.ForgotPassword.DAO.IDAOAbstractFactory;
import com.group3.ForgotPassword.DAO.IUserPasswordDAO;
import com.group3.ForgotPassword.DAO.VerificationCodeMock;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ResetCodeManagerTest {

	Guest model = null;
	IDAOAbstractFactory daoInjector;
	IUserPasswordDAO userPasswordDAO;
	IVerificationCode verificationCode;
	GmailServiceMock gmailServiceMock;

	public ResetCodeManagerTest() {

		daoInjector = DAOMockAbstractFactory.instance();
		userPasswordDAO = daoInjector.getUserDAOObj();
		verificationCode = new VerificationCodeMock();
		gmailServiceMock = new GmailServiceMock();
	}

	@Test
	public void sendCodeEmail() {

		gmailServiceMock.setSMTPClient();
		assertThat(gmailServiceMock.properties).isNotEmpty();
		assertThat(gmailServiceMock.properties).isNotNull();
		assertThat(gmailServiceMock.session).isNotNull();
	}

	@Test
	public void generateCode() {

		String code = verificationCode.getNewCode(5);
		assertThat(code).isNotNull();
		assertThat(code).isNotEmpty();
		assertEquals(code.length(), 5);
	}

	@Test
	public void checkEmailIdExistance() {

		boolean result = userPasswordDAO.isUserExist("testmail@dal.ca");
		assertThat(result).isNotNull();
		assertFalse(result);

	}
}