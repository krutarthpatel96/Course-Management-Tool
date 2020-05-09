package com.group3.BusinessModels;

import com.group3.SignUp.DAO.DAOAbstractFactory;
import com.group3.SignUp.DAO.IUserDAO;
import com.group3.SignUp.Services.IUserDetailsService;
import com.group3.SignUp.Services.ServiceAbstractFactory;

public abstract class Person {
	private String firstName;
	private String lastName;
	private String email;
	private String role;
	private String psw;

	public Person() {
		super();
	}

	public Person(String lastName, String firstName, String email, String role, String psw) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.email = email;
		this.psw = psw;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserRole() {
		return role;
	}

	public void setUserRole(String role) {
		this.role = role;
	}

	public String getEncryptedPassword() {
		return psw;
	}

	public void setEncryptedPassword(String psw) {
		this.psw = psw;
	}
	
	public void checkCredentials(IUserDAO userDAO, String lastName, String firstName, String email, String pass, String passConfirm) {
		IUserDetailsService userDetailsService = ServiceAbstractFactory.instance().creatUserDetailsService(userDAO);
		String userParameter = userDetailsService.saveUser(lastName, firstName, email, pass, passConfirm);
	}
	
	public void addMember(Guest guestModel) {
		IUserDAO userDAO = DAOAbstractFactory.instance().createUserDAO();
		userDAO.getSignUpDetailsofUser(guestModel);
	}
}