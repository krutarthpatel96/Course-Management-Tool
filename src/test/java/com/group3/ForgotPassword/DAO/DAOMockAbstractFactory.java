package com.group3.ForgotPassword.DAO;

public class DAOMockAbstractFactory implements IDAOAbstractFactory {

	public static IDAOAbstractFactory daoInjector;
	public static IUserPasswordDAO userPasswordDAO;

	private DAOMockAbstractFactory() {
	}

	public static IDAOAbstractFactory instance() {
		if (null == daoInjector) {
			daoInjector = new DAOMockAbstractFactory();
		}
		return daoInjector;
	}

	@Override
	public IUserPasswordDAO getUserDAOObj() {
		return new UserPasswordDAOMock();
	}

}