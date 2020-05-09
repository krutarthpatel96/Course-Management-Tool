package com.group3.SignUp.DAO;

public class DAOAbstractFactory implements IDAOAbstractFactory {

	public static IDAOAbstractFactory daoInjector;
	public IUserDAO userPasswordDAO;

	private DAOAbstractFactory() {
	}

	public static IDAOAbstractFactory instance() {
		if (null == daoInjector) {
			daoInjector = new DAOAbstractFactory();
		}
		return daoInjector;
	}

	public IUserDAO createUserDAO() {
		if (null == userPasswordDAO) {
			userPasswordDAO = new UserDAO();
		}
		return userPasswordDAO;
	}
}