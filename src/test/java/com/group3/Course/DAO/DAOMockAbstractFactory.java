package com.group3.Course.DAO;

public class DAOMockAbstractFactory implements IDAOAbstractFactory {

	public static IDAOAbstractFactory daoInjector;
	public static ICourseDAO courseDAO;
	public static IStudentDAO studentDAO;
	public static ITADAO taDAO;
	public static IInstructorDAO instructorDAO;

	private DAOMockAbstractFactory() {
	}

	public static IDAOAbstractFactory instance() {
		if (null == daoInjector) {
			daoInjector = new DAOMockAbstractFactory();
		}
		return daoInjector;
	}

	@Override
	public ICourseDAO createCourseDAO() {
		return new CourseDAOMock();
	}

	@Override
	public IStudentDAO createStudentDAO() {
		return new StudentDAOMock();
	}

	@Override
	public ITADAO createTADAO() {
		return new TADAOMock();
	}

	@Override
	public IInstructorDAO createInstructorDAO() {
		return new InstructorDAOMock();
	}
}