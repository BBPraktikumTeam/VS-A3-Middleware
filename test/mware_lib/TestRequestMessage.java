package mware_lib;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestRequestMessage {

	@Test
	public void testRequestMessage() {
		RequestMessage reqMsg = new RequestMessage(42, "servant", "method",
				"param");
		assertEquals(42, reqMsg.messageId());
		assertEquals("servant", reqMsg.servant());
		assertEquals("method", reqMsg.method());
		assertEquals("param", reqMsg.param());
		assertEquals("request,42,servant,method,param", reqMsg.toString());
	}
}
