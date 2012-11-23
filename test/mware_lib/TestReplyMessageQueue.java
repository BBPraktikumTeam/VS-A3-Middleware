package mware_lib;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestReplyMessageQueue {

	@Test
	public void pop() {
		assertNull(ReplyMessageQueue.pop(100));
	}

	@Test
	public void putAndPop() {
		ReplyMessage rm = new ResultMessage("result,101,val");
		ReplyMessageQueue.put(rm);
		assertSame(rm, ReplyMessageQueue.pop(101));
	}

	@Test
	public void putAndPopAndPop() {
		ReplyMessage rm = new ResultMessage("result,102,val");
		ReplyMessageQueue.put(rm);
		assertSame(rm, ReplyMessageQueue.pop(102));
		assertNull(ReplyMessageQueue.pop(102));
	}

}
