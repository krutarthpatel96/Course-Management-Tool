package com.group3.AdminAndAuthorization.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import com.group3.SignUp.DAO.IUserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.group3.BusinessModels.Guest;

class GrantInstructorAccessDAOTest {
	IGrantInstructorAccessDAO grantInstructorAccessDAO;
	IUserDAO userDAO;
	Guest falseUser, trueUser;
	ArrayList<Guest> accessUserlist;

	public GrantInstructorAccessDAOTest() {

		accessUserlist = new ArrayList<>();
		grantInstructorAccessDAO = DAOAbstractFactory.instance().createGrantInstructorAccessDAO();
		falseUser = new Guest();
		falseUser.setEmail("AtalVajpayee@dal.ca");
		falseUser.setFirstName("Atal");
		falseUser.setLastName("Vajpayee");
		falseUser.setUserRole("Guest");

		trueUser = new Guest();
		trueUser.setFirstName("Joe");
		trueUser.setLastName("Root");
		trueUser.setEmail("joe.root@dal.ca");
	}

	@Test
	final void testReturnEligibleUsersList() {
		accessUserlist = this.grantInstructorAccessDAO.returnEligibleUsersList();

		for (Guest user : accessUserlist) {
			assertFalse(falseUser.getEmail().equals(user.getEmail()));
			if (trueUser.getEmail().equals(user.getEmail())) {
				assertTrue(trueUser.getFirstName().equals(user.getFirstName()));
			}
		}
	}
}