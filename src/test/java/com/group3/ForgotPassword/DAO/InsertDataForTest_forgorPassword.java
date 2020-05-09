package com.group3.ForgotPassword.DAO;

import com.group3.DBConnectivity.ObtainDataBaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertDataForTest_forgorPassword {

	Connection connection;
	PreparedStatement statement;
	String query;

	private static Logger logger = LogManager.getLogger(InsertDataForTest_forgorPassword.class);

	public void insertDataIntoDbForTest(String MAIL_ID, String PASSWORD) {

		int queryResult;
		try {
			connection = ObtainDataBaseConnection.obtainDatabaseConnection();
			query = "insert into AUTHENTICATION_DATABASE values(?,?)";

			statement = connection.prepareStatement(query);
			statement.setString(1, MAIL_ID);
			statement.setString(2, PASSWORD);
			queryResult = statement.executeUpdate();

			connection.close();
		} catch (SQLException e) {
			logger.error(e);
		}
	}

	public String getDataFromDbForTest(String MAIL_ID) {

		ResultSet result;
		String password = null;
		try {
			connection = ObtainDataBaseConnection.obtainDatabaseConnection();
			query = "select *from AUTHENTICATION_DATABASE where MAIL_ID = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, MAIL_ID);
			result = statement.executeQuery();
			result.next();
			password = result.getString("PASSWORD");

			connection.close();
		} catch (SQLException e) {
			logger.error(e);
		}
		return password;
	}
}