package com.group3.Course.DAO;

public interface IDAOAbstractFactory {
	public ICourseDAO createCourseDAO();

	public IStudentDAO createStudentDAO();

	public ITADAO createTADAO();

	public IInstructorDAO createInstructorDAO();
}