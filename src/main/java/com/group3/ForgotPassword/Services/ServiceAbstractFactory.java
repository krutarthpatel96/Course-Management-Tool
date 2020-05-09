package com.group3.ForgotPassword.Services;

import com.group3.ForgotPassword.DAO.*;

public class ServiceAbstractFactory implements IServiceAbstractFactory {

	public static IServiceAbstractFactory serviceInjector;
	public IGmailService gmailService;
	public IResetCodeManager resetCodeManager;
	public IUpdatePasswordManager updatePasswordManager;
	public IVerificationCode verificationCode;

	private ServiceAbstractFactory() {
	}

	public static IServiceAbstractFactory instance() {
		if (null == serviceInjector) {
			serviceInjector = new ServiceAbstractFactory();
		}
		return serviceInjector;
	}

	@Override
	public IGmailService createGmailService(IEmailInjector emailInjector) {
		return new GmailService();

	}

	@Override
	public IResetCodeManager createResetCodeManager(IDAOAbstractFactory daoInjector, IEmailInjector emailInjector,
			IVerificationCode verificationCodeGenerator) {
		return new ResetCodeManager(daoInjector, emailInjector, verificationCodeGenerator);
	}

	@Override
	public IUpdatePasswordManager createUpdatePasswordManager(IDAOAbstractFactory daoInjector) {
		return new UpdatePasswordManager(daoInjector);
	}

	@Override
	public IVerificationCode createVerificationCodeGenerator() {
		return new VerificationCode();
	}
}