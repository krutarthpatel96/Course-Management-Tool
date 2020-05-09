package com.group3.SignUp.Services;

import java.util.regex.Pattern;

import com.group3.BusinessModels.Guest;
import com.group3.SignUp.DAO.IUserDAO;

public class UserDetailsService implements IUserDetailsService {
	IUserDAO userDAO;
	Guest guest;

	public UserDetailsService(IUserDAO userDAO) {

		this.userDAO = userDAO;
	}

	public String saveUser(String lastName, String firstName, String email, String pass, String passConfirm)
			throws NullPointerException {

		String emailRegex;
		String message = "";
		Pattern passwordPattern;
		boolean emailValidate;
		boolean checkInsert;

		emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
		passwordPattern = Pattern.compile(emailRegex);

		guest = new Guest(lastName, firstName, email, "Guest", pass);

		emailValidate = passwordPattern.matcher(email).matches();
		if (emailValidate == false || pass.matches(passConfirm) == false) {
			if (emailValidate == false && pass.matches(passConfirm) == false) {
				message = UserVerificationParameters.INVALID_PASSWORD_EMAIL;
			} else if (emailValidate == false) {
				message = UserVerificationParameters.INVALID_EMAIL;
			} else if (pass.matches(passConfirm) == false) {
				message = UserVerificationParameters.INVALID_PASSWORD;
			}
			return message;
		} else {
			checkInsert = false;
			checkInsert = userDAO.getSignUpDetailsofUser(guest);

			if (checkInsert) {
				message = UserVerificationParameters.SIGNUP_SUCCESS;
			} else {
				message = UserVerificationParameters.USER_EXISTS;
			}
			return message;
		}
	}

}