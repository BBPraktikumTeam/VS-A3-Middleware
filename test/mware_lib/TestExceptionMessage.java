package mware_lib;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestExceptionMessage {

	@Test
	public void testExceptionMessage() {
		ExceptionMessage excMsg = new ExceptionMessage("exception,42,java.lang.RuntimeException,SomeText");
		assertEquals(42, excMsg.messageId());
		assertEquals("java.lang.RuntimeException", excMsg.type());
		assertEquals("SomeText", excMsg.message());
		assertTrue(excMsg.exception());
		assertFalse(excMsg.result());
	}
}
