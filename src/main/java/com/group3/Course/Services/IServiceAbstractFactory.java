package com.group3.Course.Services;

import com.group3.Course.DAO.*;

public interface IServiceAbstractFactory {
	public ICourseManager createCourseManager(IDAOAbstractFactory daoInjector);

	public IStudentManager createStudentManager(IDAOAbstractFactory daoInjector, IEmailInjector emailInjector,
			IPassword password);

	public ITAManager createTAManager(IDAOAbstractFactory daoInjector);

	public IGmailService createGmailService();

	public IPassword createPasswordGenerator();
}