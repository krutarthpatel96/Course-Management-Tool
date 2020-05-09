package com.group3.SignUp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.group3.BusinessModels.Guest;
import com.group3.SignUp.DAO.*;
import com.group3.SignUp.Services.*;

@Controller
public class UserDetailsController {
	Connection conn;
	PreparedStatement statement;
	IDAOAbstractFactory daoInjector;
	IServiceAbstractFactory serviceAbstractFactory;
	IUserDAO userDAO;
	IUserDetailsService userService;
	Guest guest;

	private static Logger logger = LogManager.getLogger(UserDetailsController.class);

	public UserDetailsController() {

		daoInjector = DAOAbstractFactory.instance();
		serviceAbstractFactory = ServiceAbstractFactory.instance();
		userDAO = daoInjector.createUserDAO();
		userService = serviceAbstractFactory.creatUserDetailsService(userDAO);
	}

	@RequestMapping("/signUp")
	public ModelAndView gotoSignUpPage() {

		return new ModelAndView(UserVerificationParameters.SIGN_UP);
	}

	@RequestMapping("/formSubmit")
	public ModelAndView getSignUpDetails(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email,
			@RequestParam("psw") String psw, @RequestParam("psw-repeat") String pswRpeat)
			throws SQLException, NullPointerException {

		ModelAndView mv = new ModelAndView();
		String output;
		try {
			guest = new Guest(lastName, firstName, email, UserVerificationParameters.GUEST_USER, psw);
			output = userService.saveUser(lastName, firstName, email, psw, pswRpeat);

			if (output.contains(UserVerificationParameters.INVALID_PASSWORD_EMAIL)) {
				mv.addObject(UserVerificationParameters.MAIL_VALIDITY, UserVerificationParameters.VALID_EMAIL_MESSAGE);
				mv.addObject(UserVerificationParameters.STATUS, UserVerificationParameters.VALID_PASSWORD_MESSAGE);
				mv.setViewName(UserVerificationParameters.SIGN_UP);
			} else if (output.contains(UserVerificationParameters.INVALID_EMAIL)) {
				mv.addObject(UserVerificationParameters.MAIL_VALIDITY, UserVerificationParameters.VALID_EMAIL_MESSAGE);
				mv.setViewName(UserVerificationParameters.SIGN_UP);
			} else if (output.contains(UserVerificationParameters.INVALID_PASSWORD)) {
				mv.addObject(UserVerificationParameters.STATUS, UserVerificationParameters.VALID_PASSWORD_MESSAGE);
				mv.setViewName(UserVerificationParameters.SIGN_UP);
			} else if (output.contains(UserVerificationParameters.SIGNUP_SUCCESS)) {
				mv.addObject(UserVerificationParameters.SIGNUP_SUCCESS_MESSAGE,
						UserVerificationParameters.SIGNUP_PASSED);
				mv.setViewName(UserVerificationParameters.LOGIN);
			} else if (output.contains(UserVerificationParameters.USER_EXISTS)) {
				mv.addObject(UserVerificationParameters.USER, UserVerificationParameters.USER_EXISTS);
				mv.setViewName(UserVerificationParameters.LOGIN);
			} else {
				mv.setViewName(UserVerificationParameters.SIGN_UP);
			}

		} catch (Exception e) {
			logger.error(e);
		}
		return mv;
	}

	@RequestMapping("/login")
	public ModelAndView getLoginPage(ModelAndView mv) {

		return new ModelAndView(UserVerificationParameters.LOGIN);
	}
}
