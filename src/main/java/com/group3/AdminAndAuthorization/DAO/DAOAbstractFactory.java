package com.group3.AdminAndAuthorization.DAO;

public class DAOAbstractFactory implements IDAOAbstractFactory {
	public static IDAOAbstractFactory daoInjector;
	public IAddCourseDAO addCourseDAO;
	public IViewCoursesDAO viewCoursesDAO;
	public IDeleteCourseDAO deleteCourseDAO;
	public IDeleteUserDAO deleteUserDAO;
	public IGrantInstructorAccessDAO grantInstructorAccessDAO;
	public IUserRoleHandlerDAO userRoleHandlerDAO;
	public IInstructorHandlerDAO instructorHandlerDAO;

	private DAOAbstractFactory() {
	}

	public static IDAOAbstractFactory instance() {
		if (null == daoInjector) {
			daoInjector = new DAOAbstractFactory();
		}

		return daoInjector;
	}

	@Override
	public IAddCourseDAO createAddCourseDAO() {
		if (null == addCourseDAO) {
			addCourseDAO = new AddCourseDAO();
		}

		return addCourseDAO;
	}

	@Override
	public IViewCoursesDAO createViewCourseDAO() {
		if (null == viewCoursesDAO) {
			viewCoursesDAO = new ViewCoursesDAO();
		}

		return viewCoursesDAO;
	}

	@Override
	public IDeleteCourseDAO createDeleteCourseDAO() {
		if (null == deleteCourseDAO) {
			deleteCourseDAO = new DeleteCourseDAO();
		}

		return deleteCourseDAO;
	}

	@Override
	public IGrantInstructorAccessDAO createGrantInstructorAccessDAO() {
		if (null == grantInstructorAccessDAO) {
			grantInstructorAccessDAO = new GrantInstructorAccessDAO();
		}

		return grantInstructorAccessDAO;
	}

	@Override
	public IUserRoleHandlerDAO createUserRoleHandlerDAO() {
		if (null == userRoleHandlerDAO) {
			userRoleHandlerDAO = new UserRoleHandlerDAO();
		}

		return userRoleHandlerDAO;
	}

	@Override
	public IDeleteUserDAO createDeleteUserDAO() {
		if (null == deleteUserDAO) {
			return new DeleteUserDAO();
		}
		return deleteUserDAO;
	}

	@Override
	public IInstructorHandlerDAO createInstructorHandlerDAO() {
		if (null == instructorHandlerDAO) {
			instructorHandlerDAO = new InstructorHandlerDAO();
		}

		return instructorHandlerDAO;
	}
}