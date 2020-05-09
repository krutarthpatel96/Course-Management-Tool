package com.group3.AdminAndAuthorization.DAO;

import com.group3.AdminAndAuthorization.DAO.IDAOInjector;

import com.group3.AdminAndAuthorization.DAO.*;

public class DAOMockInjector implements IDAOInjector {
	IAddCourseDAO addCourseDAO;
	IViewCoursesDAO viewCoursesDAO;
	IDeleteCourseDAO deleteCourseDAO;
	IGrantInstructorAccessDAO grantInstructorAccessDAO;
	IInstructorHandlerDAO instructorHandlerDAO;
	IUserRoleHandlerDAO userRoleHandlerDAO;
	static DAOMockInjector daoMockInjector;
	private DAOMockInjector(){

	}
	public static DAOMockInjector instance() {
		if (null == daoMockInjector ) {
			daoMockInjector = new DAOMockInjector();
		}

		return daoMockInjector;
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