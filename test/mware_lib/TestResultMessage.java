package mware_lib;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestResultMessage {

	@Test
	public void testResultMessage() {
		ResultMessage resMsg = new ResultMessage("result,42,val");
		assertEquals(42, resMsg.messageId());
		assertEquals("val", resMsg.value());
		assertFalse(resMsg.exception());
		assertTrue(resMsg.result());
	}
}
