package com.group3.DBConnectivity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ObtainDataBaseConnection {
	static Connection databaseConnection;
	private static Map<String, String> dataBaseCrediatialList;
	private static ObtainDataBaseConnection obtainDataBaseConnectionInstance;
	private static Properties dataBaseprops;
	private static String dataBaseconfigFile;
	private static InputStream databasepropertyFile;
	private String server, port, database, username, password;
	private Logger logger = LogManager.getLogger(ObtainDataBaseConnection.class);

	private ObtainDataBaseConnection() {
		dataBaseCrediatialList = new HashMap<String, String>();
		dataBaseConfigurationLoaderModule();
	}

	public static ObtainDataBaseConnection instance() {
		if (null == obtainDataBaseConnectionInstance) {
			obtainDataBaseConnectionInstance = new ObtainDataBaseConnection();
		}
		return obtainDataBaseConnectionInstance;
	}

	private Map<String, String> dataBaseConfigurationLoaderModule() {

		try {
			dataBaseprops = new Properties();
			dataBaseconfigFile = "DevDataBaseServerConfiguration.properties";
			databasepropertyFile = getClass().getClassLoader().getResourceAsStream(dataBaseconfigFile);
			dataBaseprops.load(databasepropertyFile);

			username = dataBaseprops.getProperty("username");
			dataBaseCrediatialList.put("username", username);

			password = dataBaseprops.getProperty("password");
			dataBaseCrediatialList.put("password", password);

			database = dataBaseprops.getProperty("database");
			dataBaseCrediatialList.put("database", database);

			server = dataBaseprops.getProperty("server");
			dataBaseCrediatialList.put("server", server);

			port = dataBaseprops.getProperty("port");
			dataBaseCrediatialList.put("port", port);

		} catch (IOException e) {
			logger.error("Missing resource (Unable to load the configuration loader) ", e);
		}
		return dataBaseCrediatialList;

	}

	public static Connection obtainDatabaseConnection() {
		ObtainDataBaseConnection.instance();
		String user = dataBaseCrediatialList.get("username");
		String password = dataBaseCrediatialList.get("password");
		String database = dataBaseCrediatialList.get("database");
		String server = dataBaseCrediatialList.get("server");
		String port = dataBaseCrediatialList.get("port");
		String databaseConnectionURL = "jdbc:mysql://" + server + ":" + port + "/" + database;
		try {
			Class.forName(ConnectionParameters.DRIVER_NAME);
			databaseConnection = DriverManager.getConnection(databaseConnectionURL, user, password);

		} catch (Exception e) {

			System.out.println("Error occured while connecting to remote database : " + e);
		}
		return databaseConnection;
	}

	public static boolean terminateConnection() {
		try {

			if (databaseConnection.isClosed() == false) {

				databaseConnection.close();
			}

		} catch (SQLException e) {
			System.out.println("Error terminating connection with server " + e);
		}

		return true;

	}

}
