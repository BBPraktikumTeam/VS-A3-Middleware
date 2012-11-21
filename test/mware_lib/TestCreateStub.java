package mware_lib;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

public class TestCreateStub {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void createStub() {
		assertTrue(NameServiceStub.createStub("java.lang.String", "localhost", 12345) instanceof java.lang.Object);
		assertFalse(NameServiceStub.createStub("java.lang.String", "localhost", 12345) instanceof branch_access.Manager);
		assertFalse(NameServiceStub.createStub("java.lang.String", "localhost", 12345) instanceof cash_access.Account);

		assertTrue(NameServiceStub.createStub("branch_access.Manager", "localhost", 12345) instanceof branch_access.Manager);
		assertFalse(NameServiceStub.createStub("cash_access.Account", "localhost", 12345) instanceof branch_access.Manager);

		assertFalse(NameServiceStub.createStub("branch_access.Manager", "localhost", 12345) instanceof cash_access.Account);
		assertTrue(NameServiceStub.createStub("cash_access.Account", "localhost", 12345) instanceof cash_access.Account);
	}

}
