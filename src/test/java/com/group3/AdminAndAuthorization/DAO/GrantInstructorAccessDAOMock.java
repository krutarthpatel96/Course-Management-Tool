package com.group3.AdminAndAuthorization.DAO;

import java.util.ArrayList;

import com.group3.BusinessModels.Guest;

public class GrantInstructorAccessDAOMock implements IGrantInstructorAccessDAO {
	ArrayList<Guest> userDataSet;

	public GrantInstructorAccessDAOMock() {

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

	@Override
	public ArrayList<Guest> returnEligibleUsersList() {
		return userDataSet;
	}
}