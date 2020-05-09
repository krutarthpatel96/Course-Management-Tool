package com.group3.AdminAndAuthorization.Services;

import com.group3.AdminAndAuthorization.DAO.*;
import com.group3.BusinessModels.Course;
import com.group3.BusinessModels.Guest;

public interface IServiceAbstractFactory {
	ICourseInputValidation createCourseInputValidation();

	IAddCourseService createaddCourseService(IAddCourseDAO addCourseDAO);

	IViewCoursesService createViewCoursesService(IViewCoursesDAO viewCoursesDAO);

	IDeleteCourseService createDeleteCourseService(IDeleteCourseDAO deleteCourseDAO, Course course);

	IGrantInstructorAccessService createGrantInstructorAccessService(
			IGrantInstructorAccessDAO grantInstructorAccessDAO);

	IAdminPageServices createAdminPageServices(IInstructorHandlerDAO instructorHandlerDAO,
											   IUserRoleHandlerDAO userRoleHandlerDAO, Guest guest, String courseId);

	IExtractCourseIdService createExtractCourseIdService(String inputCourseString);

	IGrantAccessFieldsValidation createGrantAccessFieldsValidation(String courseName, String role);
}