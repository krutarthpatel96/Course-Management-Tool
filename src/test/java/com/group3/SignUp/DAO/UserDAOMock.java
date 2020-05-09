package com.group3.SignUp.DAO;

import com.group3.BusinessModels.Guest;

import java.util.ArrayList;

public class UserDAOMock implements IUserDAO {

	public static ArrayList<Guest> userList = new ArrayList<Guest>();

	public UserDAOMock() {

		Guest model = new Guest();
		model.setFirstName("Anudish");
		model.setLastName("Jain");
		model.setUserRole("Guest");
		model.setEmail("anjain@gmail.com");
		model.setEncryptedPassword("GetMeRight@1234");
		userList.add(model);
	}

	@Override
	public boolean getSignUpDetailsofUser(Guest guest) {

		boolean checkUniqueUser = true;
		for (Guest model : userList) {
			if (model.getEmail().equals(guest.getEmail())) {
				checkUniqueUser = false;
				break;
			}
		}
		if (checkUniqueUser) {
			userList.add(guest);
		}
		return checkUniqueUser;
	}
}