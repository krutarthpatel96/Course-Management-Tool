package com.group3.ForgotPassword.Services;

public class EmailInjector implements IEmailInjector {

	public static IEmailInjector emailInjector;
	public static IGmailService gmailService;

	public static IEmailInjector instance() {
		if (null == emailInjector) {
			emailInjector = new EmailInjector();
		}
		return emailInjector;
	}

	public GmailService getGmailService() {
		if (null == gmailService) {
			gmailService = new GmailService();
		}
		return new GmailService();
	}
}