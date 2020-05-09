package com.group3.AdminAndAuthorization.Services;

import com.group3.AdminAndAuthorization.DAO.*;
import com.group3.BusinessModels.Course;
import com.group3.BusinessModels.Guest;

public class ServiceInjector implements IServiceInjector {
	public static IServiceInjector serviceInjector;
	public ICourseInputValidation courseInputValidationService;
	public IAddCourseService addCourseService;
	public IViewCoursesService viewCoursesService;
	public IDeleteCourseService deleteCourseService;
	public IGrantInstructorAccessService grantInstructorAccessService;
	public IAdminPageServices adminPageServices;
	public IExtractCourseIdService extractCourseIdService;
	public IGrantAccessFieldsValidation grantAccessFieldsValidationService;

	public static IServiceInjector instance() {
		if (null == serviceInjector) {
			serviceInjector = new ServiceInjector();
		}

		return serviceInjector;
	}

	@Override
	public ICourseInputValidation createCourseInputValidation() {
		return new CourseInputValidation();
	}

	@Override
	public IAddCourseService createaddCourseService(IAddCourseDAO addCourseDAO) {
		return new AddCourseService(addCourseDAO);
	}

	@Override
	public IViewCoursesService createViewCoursesService(IViewCoursesDAO viewCoursesDAO) {
		return new ViewCoursesService(viewCoursesDAO);
	}

	@Override
	public IDeleteCourseService createDeleteCourseService(IDeleteCourseDAO deleteCourseDAO, Course course) {
		return new DeleteCourseService(deleteCourseDAO, course);

	}

	@Override
	public IGrantInstructorAccessService createGrantInstructorAccessService(
			IGrantInstructorAccessDAO grantInstructorAccessDAO) {
		return new GrantInstructorAccessService(grantInstructorAccessDAO);
	}

	@Override
	public IAdminPageServices createAdminPageServices(IInstructorHandlerDAO instructorHandlerDAO,
													  IUserRoleHandlerDAO userRoleHandlerDAO, Guest guest, String courseId) {
		return new AdminPageServices(instructorHandlerDAO, userRoleHandlerDAO, guest, courseId);

	}

	@Override
	public IExtractCourseIdService createExtractCourseIdService(String inputCourseString) {
		return new ExtractCourseIdService(inputCourseString);
	}

	@Override
	public IGrantAccessFieldsValidation createGrantAccessFieldsValidation(String courseName, String role) {
		return new GrantAccessFieldsValidation(courseName, role);

	}
}