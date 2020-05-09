package com.group3.Course.Services;

public class EmailInjector implements IEmailInjector {

	public static IEmailInjector emailInjector;
	public static IGmailService gmailService;

	public static IEmailInjector instance() {
		if (emailInjector == null) {
			emailInjector = new EmailInjector();
		}
		return emailInjector;
	}

	@Override
	public IGmailService getGmailService() {
		if (gmailService == null) {
			gmailService = new GmailService();
		}
		return gmailService;
	}
}