package com.group3.Course.DAO;

public class DAOAbstractFactory implements IDAOAbstractFactory {

	public static IDAOAbstractFactory daoInjector;
	public ICourseDAO courseDAO;
	public IStudentDAO studentDAO;
	public ITADAO taDAO;
	public IInstructorDAO instructorDAO;

	private DAOAbstractFactory() {
	}

	public static IDAOAbstractFactory instance() {
		if (null == daoInjector) {
			daoInjector = new DAOAbstractFactory();
		}
		return daoInjector;
	}

	@Override
	public ICourseDAO createCourseDAO() {
		if (null == courseDAO) {
			courseDAO = new CourseDAO();
		}
		return courseDAO;
	}

	@Override
	public IStudentDAO createStudentDAO() {
		if (null == studentDAO) {
			studentDAO = new StudentDAO();
		}
		return studentDAO;
	}

	@Override
	public ITADAO createTADAO() {
		if (null == taDAO) {
			taDAO = new TADAO();
		}
		return taDAO;
	}

	@Override
	public IInstructorDAO createInstructorDAO() {
		if (null == instructorDAO) {
			instructorDAO = new InstructorDAO();
		}
		return instructorDAO;
	}

}