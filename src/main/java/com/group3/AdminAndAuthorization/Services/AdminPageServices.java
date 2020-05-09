package com.group3.AdminAndAuthorization.Services;

import java.util.ArrayList;

import com.group3.AdminAndAuthorization.DAO.IInstructorHandlerDAO;
import com.group3.AdminAndAuthorization.DAO.IUserRoleHandlerDAO;

public class AdminPageServices implements IAdminPageServices {
	private IInstructorHandlerDAO instructorHandlerDAO;
	private IUserRoleHandlerDAO userRoleHandlerDAO;
	String role, mailId, lastName, firstName, outputMessage;
	private static final String Guest = "Guest";
	private static final String Instructor = "Instructor";
	private com.group3.BusinessModels.Guest guestmodel;
	private String CourseId;

	public AdminPageServices(IInstructorHandlerDAO instructorHandlerDAO, IUserRoleHandlerDAO userRoleHandlerDAO,
			com.group3.BusinessModels.Guest guestmodel, String CourseId) {
		this.instructorHandlerDAO = instructorHandlerDAO;
		this.userRoleHandlerDAO = userRoleHandlerDAO;
		this.guestmodel = guestmodel;
		this.CourseId = CourseId;
	}

	@Override
	public String alterUserRole() {
		// TODO Auto-generated method stub
		role = guestmodel.getUserRole();
		mailId = guestmodel.getEmail();
		lastName = guestmodel.getLastName();
		firstName = guestmodel.getFirstName();
		if (this.userRoleHandlerDAO.returnUserRole(mailId).equals(role)) {

			outputMessage = firstName + " " + lastName + " has already holds position for " + role;
		} else {
			String previousRole = this.userRoleHandlerDAO.returnUserRole(mailId);
			if (this.userRoleHandlerDAO.updateUserRole(role, mailId).length() > 0) {
				outputMessage = firstName + " " + lastName + " " + " switched their role from " + previousRole + " to "
						+ role;
			}

		}
		if (role.equals(Guest) && this.instructorHandlerDAO.isInstructorExists(mailId)) {
			if (this.instructorHandlerDAO.deleteinstructor(mailId).length() > 0) {

				System.out.println("Instructor Deleted from database");
			}

		}
		if (role.equals(Instructor)) {
			if (this.instructorHandlerDAO.isInstructorExists(mailId)) {
				ArrayList<String> courseList = this.instructorHandlerDAO.getInstructorCourses(mailId);
				for (String courseid : courseList) {
					System.out.println(CourseId + " " + courseid);
					if (courseid.equalsIgnoreCase(CourseId)) {

						outputMessage = firstName + " " + lastName + " " + "is already an instructor for the course "
								+ courseid;
						return outputMessage;
					}
				}
				outputMessage = this.instructorHandlerDAO.createNewInstructor(mailId, CourseId);
			}
		}
		return outputMessage;
	}

}
