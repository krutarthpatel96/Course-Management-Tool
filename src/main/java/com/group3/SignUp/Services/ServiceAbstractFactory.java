package com.group3.SignUp.Services;

import com.group3.SignUp.DAO.*;

public class ServiceAbstractFactory implements IServiceAbstractFactory {

	public static IServiceAbstractFactory serviceInjector;
	public IUserDetailsService userDetailsService;

	private ServiceAbstractFactory() {
	}
	
	public static IServiceAbstractFactory instance() {
		if (null == serviceInjector) {
			serviceInjector = new ServiceAbstractFactory();
		}
		return serviceInjector;
	}

	@Override
	public IUserDetailsService creatUserDetailsService(IUserDAO userDAO) {
		return new UserDetailsService(userDAO);
	}
}