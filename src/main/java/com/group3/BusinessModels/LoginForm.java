package com.group3.BusinessModels;

import com.group3.Login.DAO.DAOAbstractFactory;
import com.group3.Login.DAO.ILoginDAO;

public class LoginForm {
	private String email;
	private String password;

	public LoginForm(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public LoginForm getUser(String email) {
		ILoginDAO loginDAO = DAOAbstractFactory.instance().createLoginDAO();
		LoginForm user = loginDAO.getUserByEmail(email);
		return user;
	}
	
	public String getUserRole(String email) {
		ILoginDAO loginDAO = DAOAbstractFactory.instance().createLoginDAO();
		String userEmail = loginDAO.getRoleByEmail(email);
		return userEmail;
	}
}