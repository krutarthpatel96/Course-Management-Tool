package com.group3.AdminAndAuthorization.Services;

import com.group3.AdminAndAuthorization.DAO.IGrantInstructorAccessService;
import com.group3.BusinessModels.Guest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import com.group3.AdminAndAuthorization.DAO.DAOMockAbstractFactory;
import org.junit.jupiter.api.Test;

import com.group3.AdminAndAuthorization.DAO.IGrantInstructorAccessDAO;

class GrantInstructorAccessServiceTest {
	IGrantInstructorAccessDAO grantInstructorAccessDAO;
	IGrantInstructorAccessService grantInstructorAccessService;
	ArrayList<Guest> userDataSet;

	public GrantInstructorAccessServiceTest() {

		grantInstructorAccessDAO = DAOMockAbstractFactory.instance().createGrantInstructorAccessDAO();
		grantInstructorAccessService = ServiceAbstractFactory.instance()
				.createGrantInstructorAccessService(grantInstructorAccessDAO);

		userDataSet = new ArrayList<>();
		Guest data = new Guest();
		data.setEmail("john@dal.ca");
		data.setFirstName("John");
		data.setLastName("Kellog");
		data.setUserRole("Guest");
		userDataSet.add(data);

		data.setEmail("vlado@dal.ca");
		data.setFirstName("Vlado");
		data.setLastName("Keslji");
		data.setUserRole("instructor");
		userDataSet.add(data);

		data.setEmail("robert@dal.ca");
		data.setFirstName("Robert");
		data.setLastName("Hawkey");
		data.setUserRole("instructor");
		userDataSet.add(data);
	}

	@Test
	final void testReturnUserList() {
		ArrayList<Guest> userList = grantInstructorAccessService.returnUserList();

		for (int i = 0; i < userList.size(); ++i) {
			Guest outcome = userList.get(i);
			Guest expectedOutcome = userDataSet.get(i);
			assertEquals(outcome.getEmail(), expectedOutcome.getEmail());
			assertEquals(outcome.getFirstName(), expectedOutcome.getFirstName());
			assertEquals(outcome.getLastName(), expectedOutcome.getLastName());
			assertEquals(outcome.getUserRole(), expectedOutcome.getUserRole());
		}

	}

}
