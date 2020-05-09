package com.group3.Course.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.group3.BusinessModels.Student;
import com.group3.Course.CourseController;
import com.group3.Course.DAO.*;

public class StudentManager implements IStudentManager {
	IDAOAbstractFactory daoInjector;
	IEmailInjector emailInjector;
	ICourseDAO courseDAO;
	IGmailService gmailService;
	IPassword passwordGenerator;
	Student studentDetails;

	final int csvBannerIndex = 0, csvFNameIndex = 1, csvLNameIndex = 2, csvMailIndex = 3;
	private static Logger logger = LogManager.getLogger(StudentManager.class);

	public StudentManager(IDAOAbstractFactory daoInjector, IEmailInjector emailInjector, IPassword passwordGenerator) {

		this.courseDAO = daoInjector.createCourseDAO();
		this.gmailService = emailInjector.getGmailService();
		this.passwordGenerator = passwordGenerator;
	}

	public void addStudentToCourse(ArrayList<List<String>> studentList) {

		logger.info("ADDING STUDENTS");
		ArrayList<List<String>> students = studentList;
		String courseId = CourseController.courseId;
		String mail;
		String password;
		ArrayList<String> current_students = courseDAO.getEnrolledStudentsByCourseId(courseId);

		try {
			for (int i = 0; i < students.size(); i++) {
				mail = studentList.get(i).get(csvMailIndex);
				studentDetails = new Student();
				studentDetails.setBannerId(studentList.get(i).get(csvBannerIndex));
				studentDetails.setEmail(studentList.get(i).get(csvMailIndex));
				studentDetails.setFirstName(studentList.get(i).get(csvFNameIndex));
				studentDetails.setLastName(studentList.get(i).get(csvLNameIndex));

				if (current_students.contains(mail) == false) {
					password = passwordGenerator.getNewPassword(10);
					studentDetails.setEncryptedPassword(password);
					courseDAO.enrollStudentToCourse(studentDetails, courseId);
					gmailService.setSMTPClient();
					gmailService.prepareMail("[University Portal] Enrollment in course:" + courseId,
							"Dear student,\nYou have been enrolled in the course: " + courseId
									+ ".\nPlease contact the instructor if this is a mistake.\nYour credentials are: \n"
									+ "Mail: " + mail + "\nPassword: " + password,
							mail);
					gmailService.sendEmail();
				}
			}
			logger.info("WRITE TO DATABASE SUCCESSFUL!");
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public ArrayList<List<String>> addStudentsFromCSV(MultipartFile file) {

		logger.info("PARSING CSV FILE");
		ArrayList<List<String>> result = new ArrayList<>();
		String line;
		List<String> row;

		if (file.isEmpty() == false) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));

				while ((line = br.readLine()) != null) {
					row = Arrays.asList(line.split(","));
					result.add(row);
				}

				br.close();
				logger.info(result);
			} catch (IOException e) {
				logger.error(e);
			}
			logger.info("CSV PARSED SUCCESSFULLY!");
		}
		return result;
	}
}