package com.group3.AdminAndAuthorization.Services;

public class GrantAccessFieldsValidation implements IGrantAccessFieldsValidation {
	private String courseName, role;

	public GrantAccessFieldsValidation(String courseName, String role) {
		this.courseName = courseName;
		this.role = role;

	}

	@Override
	public String validateFields() {
		String feedBackMessage = new String();
		if (courseName.equals("Select courses")) {
			feedBackMessage = "Select course " + "\n";
		}
		if (role.equals("Select Role")) {
			feedBackMessage += "Select Role" + "\n";
		}
		return feedBackMessage;
	}
}