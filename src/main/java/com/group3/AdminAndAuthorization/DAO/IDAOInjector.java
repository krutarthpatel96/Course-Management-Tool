package com.group3.AdminAndAuthorization.DAO;

import com.group3.AdminAndAuthorization.DAO.*;

public interface IDAOInjector {
	IAddCourseDAO createAddCourseDAO();

	IViewCoursesDAO createViewCourseDAO();

	IDeleteCourseDAO createDeleteCourseDAO();

	IGrantInstructorAccessDAO createGrantInstructorAccessDAO();

	IInstructorHandlerDAO createInstructorHandlerDAO();

	IUserRoleHandlerDAO createUserRoleHandlerDAO();

	IDeleteUserDAO createDeleteUserDAO();
}