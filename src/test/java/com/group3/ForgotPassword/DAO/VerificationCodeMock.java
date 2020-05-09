package com.group3.ForgotPassword.DAO;

import com.group3.ForgotPassword.Services.IVerificationCode;

public class VerificationCodeMock implements IVerificationCode {

	@Override
	public String getNewCode(int n) {
		return "TCode";
	}

}