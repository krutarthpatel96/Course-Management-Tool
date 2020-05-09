package com.group3.ForgotPassword.Services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.group3.ForgotPassword.DAO.*;
import com.group3.ForgotPassword.DAO.IUserPasswordDAO;

public class ResetCodeManager implements IResetCodeManager {

	IGmailService gmailService;
	IUserPasswordDAO userDataAccess;
	IVerificationCode verificationCodeGenerator;
	private String generated_code;

	private static Logger logger = LogManager.getLogger(ResetCodeManager.class);

	public ResetCodeManager(IDAOAbstractFactory userDAOFactory, IEmailInjector emailInjector,
			IVerificationCode verificationCodeGenerator) {

		gmailService = emailInjector.getGmailService();
		userDataAccess = userDAOFactory.getUserDAOObj();
		this.verificationCodeGenerator = verificationCodeGenerator;
	}

	public ModelAndView checkEmailIdExistance(String email) {

		ModelAndView mv = new ModelAndView();
		if (userDataAccess.isUserExist(email) == false) {
			logger.info("Email Id not exists!");
			mv.addObject("status", "We couldn't find account associated with this email id");
			mv.setViewName("EnterEmail.html");
		} else {
			logger.info("Email Id already exists!");
			mv.addObject("status", "");
			mv.setViewName("EnterCode.html");
		}
		return mv;
	}

	public void sendCodeEmail(String email) {

		gmailService.setSMTPClient();
		gmailService.prepareMail("[University Portal] Please reset your password",
				"You have requested a password reset for your account.\nBelow is the code that you have to use to enter new password \n\n\n"
						+ generated_code + "\n\n\n If you don't wish to reset your password, disregard this email.",
				email);
		gmailService.sendEmail();
		logger.info("Code sent via email!");
	}

	public String generateCode(int length) {

		final int code_length = 5;
		generated_code = verificationCodeGenerator.getNewCode(code_length);
		logger.info("Code Generated!");

		return generated_code;
	}
}