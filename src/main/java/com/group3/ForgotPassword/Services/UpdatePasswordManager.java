package com.group3.ForgotPassword.Services;

import org.springframework.web.servlet.ModelAndView;

import com.group3.ForgotPassword.DAO.*;

public class UpdatePasswordManager implements IUpdatePasswordManager {

	IUserPasswordDAO userDataAccess;

	public UpdatePasswordManager(IDAOAbstractFactory DAOInjector) {

		userDataAccess = DAOInjector.getUserDAOObj();
	}

	public ModelAndView compareCode(String code_input, String generated_code) {

		ModelAndView mv = new ModelAndView();
		if (code_input.equals(generated_code)) {
			mv.addObject("invalidcode", "no");
			mv.setViewName("NewPassword.html");
		} else {
			mv.addObject("invalidcode", "Inavlid Code! Try again!");
			mv.setViewName("EnterCode.html");
		}
		return mv;
	}

	public void updatePassword(String email, String password) {

		userDataAccess.updateNewPassword(email, password);
	}
}