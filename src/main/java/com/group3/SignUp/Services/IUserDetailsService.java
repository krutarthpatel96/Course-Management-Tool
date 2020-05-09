package com.group3.SignUp.Services;

public interface IUserDetailsService {
	public String saveUser(String lastName, String firstName, String email, String psw, String pswRepeat);
}
