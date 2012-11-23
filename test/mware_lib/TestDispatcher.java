package mware_lib;

import static org.junit.Assert.*;

import java.util.concurrent.Semaphore;

import org.junit.AfterClass;
import org.junit.Test;

public class TestDispatcher {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void clientDispatchResultMessage() {
		assertNull(ReplyMessageQueue.pop(201));
		final Semaphore sem1 = MessageSemaphores.create(201);
		new Thread() {
			public void run() {
				try {
					sem1.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		assertEquals(0, sem1.availablePermits());
		Dispatcher.dispatch("result,201,val1");
		ReplyMessage replyMsg1 = ReplyMessageQueue.pop(201);
		assertEquals(201, replyMsg1.messageId());
		assertTrue(replyMsg1.result());
		ResultMessage resultMsg = (ResultMessage) replyMsg1;
		assertEquals("val1", resultMsg.value());
		assertEquals(1, sem1.availablePermits());
	}

	@Test
	public void clientDispatchExceptionMessage() {
		assertNull(ReplyMessageQueue.pop(202));
		final Semaphore sem2 = MessageSemaphores.create(202);
		new Thread() {
			public void run() {
				try {
					sem2.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		assertEquals(0, sem2.availablePermits());
		Dispatcher
				.dispatch("exception,202,java.lang.RuntimeException,SomeText");
		ReplyMessage replyMsg2 = ReplyMessageQueue.pop(202);
		assertEquals(202, replyMsg2.messageId());
		assertTrue(replyMsg2.exception());
		ExceptionMessage exceptionMsg = (ExceptionMessage) replyMsg2;
		assertEquals("java.lang.RuntimeException", exceptionMsg.type());
		assertEquals("SomeText", exceptionMsg.message());
		assertEquals(1, sem2.availablePermits());
	}

	@Test
	public void serverDispatchWithoutCommunicator() {
		Skeleton skel = new DummySkeleton("aServant");
		DummySkeleton ds = (DummySkeleton) skel;
		assertEquals(0, ds.msgId());
		assertEquals("DEFAULT_METHOD", ds.method());
		assertEquals("DEFAULT_PARAM", ds.param());

		SkeletonBindings.addSkeleton(skel);
		Dispatcher.dispatch("request,211,aServant,aMethod,aParam", null);

		assertEquals(211, ds.msgId());
		assertEquals("aMethod", ds.method());
		assertEquals("aParam", ds.param());
	}

}
