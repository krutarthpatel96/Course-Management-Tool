package com.group3.AdminAndAuthorization.DAO;

import static org.junit.jupiter.api.Assertions.*;

import com.group3.AdminAndAuthorization.DAO.IUserRoleHandlerDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRoleHandlerDAOTest {
	IUserRoleHandlerDAO userRoleHandlerDAO;
	String mailId, role, switchedRole;

	public UserRoleHandlerDAOTest() {
		userRoleHandlerDAO = DAOAbstractFactory.instance().createUserRoleHandlerDAO();
		switchedRole = "Instructor";
	}

	@Test
	final void testUpdateUserRole() {

		mailId = "dwane123@dal.ca";
		role = this.userRoleHandlerDAO.returnUserRole(mailId);

		assertFalse(this.userRoleHandlerDAO.updateUserRole(role, mailId).isEmpty());
		assertTrue(this.userRoleHandlerDAO.updateUserRole(role, mailId).length() > 0);
	}

	@Test
	final void testReturnUserRole() {

		mailId = "joe.root@dal.ca";
		role = this.userRoleHandlerDAO.returnUserRole(mailId);

		assertFalse(role.equalsIgnoreCase("TA"));
		assertTrue(role.equalsIgnoreCase("Instructor") || role.equalsIgnoreCase("Guest"));
	}

}
