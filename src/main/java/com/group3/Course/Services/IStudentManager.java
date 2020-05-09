package com.group3.Course.Services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IStudentManager {
	public void addStudentToCourse(ArrayList<List<String>> studentList);

	public ArrayList<List<String>> addStudentsFromCSV(MultipartFile file);
}
