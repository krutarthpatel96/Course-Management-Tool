package com.group3.ForgotPassword.DAO;

public class DAOAbstractFactory implements IDAOAbstractFactory {

	public static IDAOAbstractFactory daoInjector;
	public IUserPasswordDAO userPasswordDAO;

	private DAOAbstractFactory() {
	}

	public static IDAOAbstractFactory instance() {
		if (null == daoInjector) {
			daoInjector = new DAOAbstractFactory();
		}
		return daoInjector;
	}

	public IUserPasswordDAO getUserDAOObj() {
		if (null == userPasswordDAO) {
			userPasswordDAO = new UserPasswordDAO();
		}
		return userPasswordDAO;
	}
}