package com.group3.AdminAndAuthorization.DAO;

import com.group3.DBConnectivity.ObtainDataBaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteUserDAO implements IDeleteUserDAO {
	String query;
	Connection connection;
	PreparedStatement statement;
	Logger logger = LogManager.getLogger(DeleteUserDAO.class);

	@Override
	public void deleteUser(String mailId) {
		connection = ObtainDataBaseConnection.obtainDatabaseConnection();
		query = "DELETE FROM USER_DATABASE WHERE MAIL_ID = ?";
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, mailId);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("error occured while deleting the user from database");
		}
	}
}
