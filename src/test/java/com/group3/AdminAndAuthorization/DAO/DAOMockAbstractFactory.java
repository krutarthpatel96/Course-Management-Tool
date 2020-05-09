package com.group3.AdminAndAuthorization.DAO;

import com.group3.AdminAndAuthorization.DAO.IDAOAbstractFactory;
import com.group3.AdminAndAuthorization.DAO.*;

public class DAOMockAbstractFactory implements IDAOAbstractFactory {
	IAddCourseDAO addCourseDAO;
	IViewCoursesDAO viewCoursesDAO;
	IDeleteCourseDAO deleteCourseDAO;
	IGrantInstructorAccessDAO grantInstructorAccessDAO;
	IInstructorHandlerDAO instructorHandlerDAO;
	IUserRoleHandlerDAO userRoleHandlerDAO;
	public static IDAOAbstractFactory daoInjector;

	private DAOMockAbstractFactory() {
	}

	public static IDAOAbstractFactory instance() {
		if (null == daoInjector) {
			daoInjector = new DAOMockAbstractFactory();
		}
		return daoInjector;
	}

	@Override
	public IAddCourseDAO createAddCourseDAO() {
		return new AddCourseDAOMock();

	}

	@Override
	public IViewCoursesDAO createViewCourseDAO() {
		return new ViewCoursesDAOMock();
	}

	@Override
	public IDeleteCourseDAO createDeleteCourseDAO() {
		return new DeleteCourseDAOMock();
	}

	@Override
	public IGrantInstructorAccessDAO createGrantInstructorAccessDAO() {
		return new GrantInstructorAccessDAOMock();
	}

	@Override
	public IInstructorHandlerDAO createInstructorHandlerDAO() {
		return new InstructorHandlerDAOMock();
	}

	@Override
	public IUserRoleHandlerDAO createUserRoleHandlerDAO() {
		return new UserRoleHandlerDAOMock();
	}

	@Override
	public IDeleteUserDAO createDeleteUserDAO() {
		return new DeleteUserDAO();
	}
}