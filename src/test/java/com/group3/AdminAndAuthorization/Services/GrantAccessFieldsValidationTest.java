package com.group3.AdminAndAuthorization.Services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class GrantAccessFieldsValidationTest {
	
	private IGrantAccessFieldsValidation grantAccessFieldsValidation;
	IServiceAbstractFactory serviceInjector;

	public GrantAccessFieldsValidationTest(){
		 serviceInjector = ServiceAbstractFactory.instance();
	}

	@Test
	final void testValidateFields() {
		grantAccessFieldsValidation =serviceInjector.createGrantAccessFieldsValidation("CSCI6770-Graphics", "Instructor");
	    assertFalse(grantAccessFieldsValidation.validateFields().length() > 0);

	}
	@Test
	final void testValidateFieldsCaseInstructor(){
		grantAccessFieldsValidation =serviceInjector.createGrantAccessFieldsValidation("Select courses", "Instructor");
		assertTrue(grantAccessFieldsValidation.validateFields().length() > 0);
	}

	@Test
	final void testValidateFieldsCaseSelectRole(){
		grantAccessFieldsValidation =serviceInjector.createGrantAccessFieldsValidation("CSCI6770-Graphics", "Select Role");
		assertTrue(grantAccessFieldsValidation.validateFields().length() > 0);
	}

	@Test
	final void testValidateFieldsCaseSelectRoleAndCourse(){
		grantAccessFieldsValidation =serviceInjector.createGrantAccessFieldsValidation("Select courses", "Select Role");
		assertTrue(grantAccessFieldsValidation.validateFields().length() > 0);
	}
}
