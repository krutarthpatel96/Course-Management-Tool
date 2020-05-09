package com.group3.AdminAndAuthorization.DAO;

import java.util.ArrayList;

import com.group3.BusinessModels.Guest;

public class GrantInstructorAccessService implements IGrantInstructorAccessService {
	private ArrayList<Guest> userList;
	private IGrantInstructorAccessDAO grantInstructorAccessDAO;

	public GrantInstructorAccessService(IGrantInstructorAccessDAO grantInstructorAccessDAO) {
		this.grantInstructorAccessDAO = grantInstructorAccessDAO;
		userList = new ArrayList<>();
	}

	@Override
	public ArrayList<Guest> returnUserList() {
		userList = this.grantInstructorAccessDAO.returnEligibleUsersList();
		return userList;
	}
}