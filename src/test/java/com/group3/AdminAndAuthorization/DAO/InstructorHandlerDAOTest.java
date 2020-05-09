package com.group3.AdminAndAuthorization.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import com.group3.AdminAndAuthorization.DAO.IInstructorHandlerDAO;
import com.group3.AdminAndAuthorization.DAO.DAOAbstractFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InstructorHandlerDAOTest {
	IInstructorHandlerDAO iInstructorHandlerDAO;
	ArrayList<String> courseList;
	String mailId, courseId;

	@BeforeEach
	void setUp() throws Exception {
		iInstructorHandlerDAO = DAOAbstractFactory.instance().createInstructorHandlerDAO();
		courseList = new ArrayList<>();
	}

	@Test
	final void testCreateNewInstructor() {

		mailId = "Caroline@dal.ca";
		courseId = "CSCI67000$%8&kjadjkk";
		assertFalse(this.iInstructorHandlerDAO.createNewInstructor(mailId, courseId).length() > 0);
		courseId = "CSCI6700";
		assertTrue(this.iInstructorHandlerDAO.createNewInstructor(mailId, courseId).length() > 0);
		this.iInstructorHandlerDAO.deleteinstructor(mailId);
	}

	@Test
	final void testIsInstructorExists() {

		mailId = "joe.root@dal.ca";
		assertFalse(this.iInstructorHandlerDAO.isInstructorExists(mailId) == false);
		assertTrue(this.iInstructorHandlerDAO.isInstructorExists(mailId));
	}

	@Test
	final void testDeleteinstructor() {

		mailId = "binny.rogers@dal.ca";
		assertFalse(this.iInstructorHandlerDAO.deleteinstructor(mailId).length() > 0);
		this.iInstructorHandlerDAO.createNewInstructor(mailId, "CSCT5408");
		assertTrue(this.iInstructorHandlerDAO.deleteinstructor(mailId).length() > 0);
	}

	@Test
	final void testGetInstructorCourses() {

		mailId = "binny.rogers@dal.ca";
		courseId = "CSCT5400";
		courseList = this.iInstructorHandlerDAO.getInstructorCourses(mailId);
		this.iInstructorHandlerDAO.createNewInstructor(mailId, courseId);
		courseList = this.iInstructorHandlerDAO.getInstructorCourses(mailId);
		this.iInstructorHandlerDAO.deleteinstructor(mailId);
	}
}