package com.group3.ForgotPassword.Services;

public class VerificationCode implements IVerificationCode {

	public String getNewCode(int n) {

		String pattern = "0123456789";
		StringBuilder sb = new StringBuilder(n);
		int index;

		for (int i = 0; i < n; i++) {
			index = (int) (pattern.length() * Math.random());
			sb.append(pattern.charAt(index));
		}
		return sb.toString();
	}
}