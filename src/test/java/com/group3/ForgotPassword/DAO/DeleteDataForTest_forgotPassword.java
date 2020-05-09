package com.group3.ForgotPassword.DAO;

import com.group3.DBConnectivity.ObtainDataBaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteDataForTest_forgotPassword {

	Connection connection;
	PreparedStatement statement;
	String query;

	private static Logger logger = LogManager.getLogger(DeleteDataForTest_forgotPassword.class);

	public void deleteDataFromDbForTest(String MAIL_ID) {

		int queryResult;
		try {
			connection = ObtainDataBaseConnection.obtainDatabaseConnection();
			query = "delete from AUTHENTICATION_DATABASE where MAIL_ID = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, MAIL_ID);
			queryResult = statement.executeUpdate();

			connection.close();
		} catch (SQLException e) {
			logger.error(e);
		}
	}

}