package com.group3.SignUp.Services;

import com.group3.SignUp.DAO.IUserDAO;

public interface IServiceAbstractFactory {
	public IUserDetailsService creatUserDetailsService(IUserDAO userDAO);
}
