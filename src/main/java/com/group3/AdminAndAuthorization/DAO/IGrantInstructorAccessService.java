package com.group3.AdminAndAuthorization.DAO;

import java.util.ArrayList;

import com.group3.BusinessModels.Guest;

public interface IGrantInstructorAccessService {
	ArrayList<Guest> returnUserList();
}
