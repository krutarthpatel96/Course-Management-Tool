package com.group3.AdminAndAuthorization.DAO;

public class DAOInjector implements IDAOInjector {
	static IDAOInjector daoInjector;
	static IAddCourseDAO addCourseDAO;
	static IViewCoursesDAO viewCoursesDAO;
	static IDeleteCourseDAO deleteCourseDAO;
	static IGrantInstructorAccessDAO grantInstructorAccessDAO;
	static IUserRoleHandlerDAO userRoleHandlerDAO;
	static IInstructorHandlerDAO instructorHandlerDAO;

	public static IDAOInjector instance() {
		if (null == daoInjector) {
			daoInjector = new DAOInjector();
		}

		return daoInjector;
	}

	@Override
	public IAddCourseDAO createAddCourseDAO() {
		if (addCourseDAO == null) {
			addCourseDAO = new AddCourseDAO();
		}

		return addCourseDAO;
	}

	@Override
	public IViewCoursesDAO createViewCourseDAO() {
		if (viewCoursesDAO == null) {
			viewCoursesDAO = new ViewCoursesDAO();
		}

		return viewCoursesDAO;
	}

	@Override
	public IDeleteCourseDAO createDeleteCourseDAO() {
		if (deleteCourseDAO == null) {
			deleteCourseDAO = new DeleteCourseDAO();
		}

		return deleteCourseDAO;
	}

	@Override
	public IGrantInstructorAccessDAO createGrantInstructorAccessDAO() {
		if (grantInstructorAccessDAO == null) {
			grantInstructorAccessDAO = new GrantInstructorAccessDAO();
			;
		}

		return grantInstructorAccessDAO;
	}

	@Override
	public IUserRoleHandlerDAO createUserRoleHandlerDAO() {
		if (userRoleHandlerDAO == null) {
			userRoleHandlerDAO = new UserRoleHandlerDAO();
		}

		return userRoleHandlerDAO;
	}

	@Override
	public IDeleteUserDAO createDeleteUserDAO() {
		return new DeleteUserDAO();
	}

	@Override
	public IInstructorHandlerDAO createInstructorHandlerDAO() {
		if (instructorHandlerDAO == null) {
			instructorHandlerDAO = new InstructorHandlerDAO();
		}

		return instructorHandlerDAO;
	}
}