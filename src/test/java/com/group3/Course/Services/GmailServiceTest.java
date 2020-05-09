package com.group3.Course.Services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GmailServiceTest {

	public static GmailServiceMock gmailServiceMock;

	public GmailServiceTest() {
		gmailServiceMock = new GmailServiceMock();
	}

	@Test
	void setSMTPClientTest() {

		gmailServiceMock.setSMTPClient();
		assertThat(gmailServiceMock.properties).isNotEmpty();
		assertThat(gmailServiceMock.properties).isNotNull();
		assertThat(gmailServiceMock.session).isNotNull();
	}

	@Test
	void prepareMessageTest() {

		gmailServiceMock.prepareMail("Test Subject", "Test Message", "Test Recepient");
		assertThat(gmailServiceMock.msg).isNotNull();
	}

	@Test
	void propertiesTest() {

		gmailServiceMock.setSMTPClient();
		assertThat(gmailServiceMock.properties).isNotNull();
		assertThat(gmailServiceMock.properties).isNotEmpty();
	}

	@Test
	void sessionTest() {
		gmailServiceMock.setSMTPClient();
		assertThat(gmailServiceMock.session).isNotNull();
	}

	@Test
	void msgTest() {
		gmailServiceMock.prepareMail("Test Subject", "Test Message", "Test Recepient");
		assertThat(gmailServiceMock.msg).isNotNull();
	}
}