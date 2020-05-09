package com.group3.ForgotPassword;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.group3.ForgotPassword.DAO.*;
import com.group3.ForgotPassword.Services.*;

@Controller
public class ForgetPasswordController {

	String generated_code;
	String email;

	IDAOAbstractFactory daoInjector;
	IEmailInjector emailInjector;
	IServiceAbstractFactory serviceAbstractFactory;
	IVerificationCode verificationCodeGenerator;
	IResetCodeManager resetCodeManager;
	IUpdatePasswordManager updatePasswordManager;

	public ForgetPasswordController() {

		daoInjector = DAOAbstractFactory.instance();
		emailInjector = EmailInjector.instance();
		serviceAbstractFactory = ServiceAbstractFactory.instance();
		verificationCodeGenerator = serviceAbstractFactory.createVerificationCodeGenerator();
		resetCodeManager = serviceAbstractFactory.createResetCodeManager(daoInjector, emailInjector,
				verificationCodeGenerator);
		updatePasswordManager = serviceAbstractFactory.createUpdatePasswordManager(daoInjector);
	}

	@RequestMapping("/enterEmail")
	public String enterEmail() {
		return "EnterEmail";
	}

	@RequestMapping("/enterCode")
	public ModelAndView enterCode(String email) {
		final int code_length = 8;
		this.email = email;
		ModelAndView mv;
		mv = resetCodeManager.checkEmailIdExistance(email);
		generated_code = resetCodeManager.generateCode(code_length);
		resetCodeManager.sendCodeEmail(email);
		return mv;
	}

	@RequestMapping("/checkCode")
	public ModelAndView checkCode(String code_input) {
		ModelAndView mv;
		System.out.println("UserCode: " + code_input);
		System.out.println("GeneratedCode: " + generated_code);
		mv = updatePasswordManager.compareCode(code_input, generated_code);
		return mv;
	}

	@RequestMapping("/passwordUpdater")
	public String passwordUpdater(String password) {
		System.out.println("Password: " + password);
		System.out.println("email for Password to update: " + email);
		updatePasswordManager.updatePassword(email, password);
		return "login";
	}
}
