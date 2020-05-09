package com.group3.Course;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.group3.BusinessModels.Course;
import com.group3.Course.DAO.DAOAbstractFactory;
import com.group3.Course.DAO.IDAOAbstractFactory;
import com.group3.Course.Services.*;

@Controller
public class StudentCSVController {

	IDAOAbstractFactory daoInjector;
	IServiceAbstractFactory serviceAbstractFactory;
	IStudentManager studentManager;
	IEmailInjector emailInjector;
	IPassword passwordGenerator;
	Course courseModel;
	private static Logger logger = LogManager.getLogger(StudentCSVController.class);

	public StudentCSVController() {

		daoInjector = DAOAbstractFactory.instance();
		emailInjector = EmailInjector.instance();
		serviceAbstractFactory = ServiceAbstractFactory.instance();
		passwordGenerator = serviceAbstractFactory.createPasswordGenerator();
		studentManager = serviceAbstractFactory.createStudentManager(daoInjector, emailInjector, passwordGenerator);
	}

	@RequestMapping("/importCSV")
	public ModelAndView showImportXMLPage() {

		ModelAndView mv = new ModelAndView();
		courseModel = new Course();
		courseModel.setCourseId(CourseController.courseId);
		courseModel.setCourseName(CourseController.courseName);
		mv.addObject("courseInfo", courseModel);
		mv.setViewName("importCSV");
		return mv;
	}

	@PostMapping("/upload-csv-file")
	public ModelAndView uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {

		ArrayList<List<String>> rows = studentManager.addStudentsFromCSV(file);
		studentManager.addStudentToCourse(rows);

		ModelAndView mv = new ModelAndView();
		mv.addObject("studentList", rows);
		mv.setViewName("showImportedStudents.html");
		return mv;
	}
}