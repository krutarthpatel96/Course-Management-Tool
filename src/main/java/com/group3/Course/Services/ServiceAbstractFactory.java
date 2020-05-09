package com.group3.Course.Services;

import com.group3.Course.DAO.*;

public class ServiceAbstractFactory implements IServiceAbstractFactory {
	public static IServiceAbstractFactory serviceInjector;
	public ICourseManager courseManager;
	public IStudentManager studentManager;
	public ITAManager taManager;
	public IGmailService gmailService;
	public IPassword passwordGenerator;

	private ServiceAbstractFactory() {
	}

	public static IServiceAbstractFactory instance() {
		if (null == serviceInjector) {
			serviceInjector = new ServiceAbstractFactory();
		}
		return serviceInjector;
	}

	@Override
	public ICourseManager createCourseManager(IDAOAbstractFactory daoInjector) {
		return new CourseManager(daoInjector);
	}

	@Override
	public IStudentManager createStudentManager(IDAOAbstractFactory daoInjector, IEmailInjector emailInjector,
			IPassword password) {
		return new StudentManager(daoInjector, emailInjector, password);
	}

	@Override
	public ITAManager createTAManager(IDAOAbstractFactory daoInjector) {
		return new TAManager(daoInjector);
	}

	@Override
	public IGmailService createGmailService() {
		return new GmailService();
	}

	@Override
	public IPassword createPasswordGenerator() {
		return new PasswordGenerator();

	}
}