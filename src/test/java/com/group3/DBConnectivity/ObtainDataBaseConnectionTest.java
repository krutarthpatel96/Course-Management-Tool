package com.group3.DBConnectivity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObtainDataBaseConnectionTest {
	Connection dataBaseconnection;

	@BeforeEach
	void setUp() throws Exception {

		dataBaseconnection = ObtainDataBaseConnection.obtainDatabaseConnection();
	}

	@Test
	final void testObtainDatabaseConnection() {
		assertNotNull(dataBaseconnection);
	}

	@Test
	final void testTerminateConnection() {
		assertTrue(ObtainDataBaseConnection.terminateConnection());
	}

}
