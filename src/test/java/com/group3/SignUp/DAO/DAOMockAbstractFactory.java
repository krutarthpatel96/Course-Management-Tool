package com.group3.SignUp.DAO;

public class DAOMockAbstractFactory implements IDAOAbstractFactory {

	public static IDAOAbstractFactory daoInjector;
	public static IUserDAO userDAO;

	private DAOMockAbstractFactory() {
	}

	public static IDAOAbstractFactory instance() {
		if (null == daoInjector) {
			daoInjector = new DAOMockAbstractFactory();
		}
		return daoInjector;
	}

	@Override
	public IUserDAO createUserDAO() {
		return new UserDAOMock();
	}

}