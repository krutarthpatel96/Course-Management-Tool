package com.group3.ForgotPassword.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.group3.DBConnectivity.ObtainDataBaseConnection;

public class UserPasswordDAO implements IUserPasswordDAO {
	Connection connection;
	PreparedStatement statement;
	String query;

	public boolean isUserExist(String email) {

		boolean queryResult = false;
		ResultSet result = null;

		try {
			connection = ObtainDataBaseConnection.obtainDatabaseConnection();
			query = "SELECT * FROM AUTHENTICATION_DATABASE WHERE MAIL_ID = ?";

			statement = connection.prepareStatement(query);
			statement.setString(1, email);

			result = statement.executeQuery();
			queryResult = result.next();

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return queryResult;
	}

	public void updateNewPassword(String email, String password) {
		int queryResult;
		try {
			connection = ObtainDataBaseConnection.obtainDatabaseConnection();
			query = "update AUTHENTICATION_DATABASE set PASSWORD = ? where MAIL_ID = ?";

			statement = connection.prepareStatement(query);
			statement.setString(1, password);
			statement.setString(2, email);

			queryResult = statement.executeUpdate();
			System.out.println(queryResult + " records updated!");

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}