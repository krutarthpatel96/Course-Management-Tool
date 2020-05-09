package com.group3.ForgotPassword.DAO;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserPasswordDAOTest {

	IDAOAbstractFactory daoInjector;
	IUserPasswordDAO userPasswordDAO;
	InsertDataForTest_forgorPassword insert;
	DeleteDataForTest_forgotPassword delete;

	public UserPasswordDAOTest() {

		daoInjector = DAOAbstractFactory.instance();
		userPasswordDAO = daoInjector.getUserDAOObj();
		insert = new InsertDataForTest_forgorPassword();
		delete = new DeleteDataForTest_forgotPassword();
	}

	@Test
	public void isUserExist() {

		insert.insertDataIntoDbForTest("jwick@dal.ca", "password");

		boolean result = userPasswordDAO.isUserExist("jwick@dal.ca");
		assertThat(result).isNotNull();
		assertTrue(result);

		result = userPasswordDAO.isUserExist("abc@dal.ca");
		assertThat(result).isNotNull();
		assertFalse(result);

		delete.deleteDataFromDbForTest("jwick@dal.ca");
	}

	@Test
	public void updateNewPassword() {

		insert.insertDataIntoDbForTest("jwick@dal.ca", "password");

		userPasswordDAO.updateNewPassword("jwick@dal.ca", "hello@123");
		String password = insert.getDataFromDbForTest("jwick@dal.ca");
		assertThat(password).isNotNull();
		assertThat(password).isNotEmpty();
		assertEquals("hello@123", password);

		delete.deleteDataFromDbForTest("jwick@dal.ca");

	}

}