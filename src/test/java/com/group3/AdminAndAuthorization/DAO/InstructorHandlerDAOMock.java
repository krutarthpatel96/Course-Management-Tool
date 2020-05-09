package com.group3.AdminAndAuthorization.DAO;

import com.group3.AdminAndAuthorization.DAO.IInstructorHandlerDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class InstructorHandlerDAOMock implements IInstructorHandlerDAO {
	private HashMap<String, String> instructorDataSet;

	public InstructorHandlerDAOMock() {

		instructorDataSet = new HashMap<String, String>();
		instructorDataSet.put("john@dal.ca", "CSCI7606");
		instructorDataSet.put("vlado@dal.ca", "CSCI8000");
		instructorDataSet.put("robert@dal.ca", "CSCI5308");
		instructorDataSet.put("robert@dal.ca", "CSCI7308");
	}

	@Override
	public String createNewInstructor(String MailId, String CourseId) {

		instructorDataSet.put(MailId, CourseId);
		String feedbackMessage = "Instructor Assigned for " + CourseId;
		return feedbackMessage;
	}

	@Override
	public boolean isInstructorExists(String MailId) {

		Iterator datasetIterator = instructorDataSet.entrySet().iterator();
		while (datasetIterator.hasNext()) {
			Map.Entry mapElement = (Map.Entry) datasetIterator.next();
			if (mapElement.getKey().equals(MailId)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String deleteinstructor(String MailId) {

		String feedbackMessage;
		instructorDataSet.remove(MailId);
		feedbackMessage = "Instructor deleted";
		return feedbackMessage;
	}

	@Override
	public ArrayList<String> getInstructorCourses(String MaildId) {

		ArrayList<String> courseList = new ArrayList<>();
		Iterator datasetIterator = instructorDataSet.entrySet().iterator();

		while (datasetIterator.hasNext()) {
			Map.Entry mapElement = (Map.Entry) datasetIterator.next();
			if (mapElement.getKey().equals(MaildId)) {
				courseList.add(mapElement.getValue().toString());
			}
		}
		return courseList;
	}
}