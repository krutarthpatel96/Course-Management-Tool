package com.group3.AdminAndAuthorization.DAO;

import java.util.ArrayList;

import com.group3.BusinessModels.Guest;

public class UserRoleHandlerDAOMock implements IUserRoleHandlerDAO {

	ArrayList<Guest> userDataSet;

	public UserRoleHandlerDAOMock() {

		userDataSet = new ArrayList<>();
		Guest data = new Guest();
		data.setEmail("john@dal.ca");
		data.setFirstName("John");
		data.setLastName("Kellog");
		data.setUserRole("Guest");
		userDataSet.add(data);

		data = new Guest();
		data.setEmail("vlado@dal.ca");
		data.setFirstName("Vlado");
		data.setLastName("Keslji");
		data.setUserRole("instructor");
		userDataSet.add(data);

		data = new Guest();
		data.setEmail("robert@dal.ca");
		data.setFirstName("Robert");
		data.setLastName("Hawkey");
		data.setUserRole("instructor");
		userDataSet.add(data);
	}

	@Override
	public String updateUserRole(String role, String maildId) {
		String feedbackMessage = new String();

		for (Guest model : userDataSet) {
			if (model.getEmail().equals(maildId)) {
				model.setUserRole(role);
				userDataSet.set(userDataSet.indexOf(model), model);
				feedbackMessage = "Role of user is altered to " + role;
			}
		}
		return feedbackMessage;
	}

	@Override
	public String returnUserRole(String mailId) {
		String feedbackMessage = new String();

		for (Guest model : userDataSet) {
			if (model.getEmail().equals(mailId)) {
				feedbackMessage = model.getUserRole();
			}
		}
		return feedbackMessage;
	}
}