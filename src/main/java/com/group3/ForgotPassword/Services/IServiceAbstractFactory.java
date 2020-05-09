package com.group3.ForgotPassword.Services;

import com.group3.ForgotPassword.DAO.*;

public interface IServiceAbstractFactory {
	public IGmailService createGmailService(IEmailInjector emailInjector);

	public IResetCodeManager createResetCodeManager(IDAOAbstractFactory daoInjector, IEmailInjector emailInjector,
			IVerificationCode verificationCodeGenerator);

	public IUpdatePasswordManager createUpdatePasswordManager(IDAOAbstractFactory daoInjector);

	public IVerificationCode createVerificationCodeGenerator();
}