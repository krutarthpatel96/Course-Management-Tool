package com.group3.AdminAndAuthorization.DAO;

public interface IUserRoleHandlerDAO {
	String updateUserRole(String Role, String MaildId);

	String returnUserRole(String MailId);
}
