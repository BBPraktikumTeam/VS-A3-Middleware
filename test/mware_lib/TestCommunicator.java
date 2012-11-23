package mware_lib;

import static org.junit.Assert.*;

import java.net.InetSocketAddress;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestCommunicator {

	private static final int SERVER_PORT = 10666;
	
	private static CommunicatorLauncher commLauncher;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		commLauncher = new CommunicatorLauncher(SERVER_PORT);
		commLauncher.start();
	}

	@Test
	public void communicator() {
		assertEquals(0, CommunicatorBindings.numberOfBindings());

		Communicator clientComm = CommunicatorBindings
				.getCommunicator(new InetSocketAddress("localhost", SERVER_PORT));

		assertEquals(1, CommunicatorBindings.numberOfBindings());
		assertSame(clientComm, CommunicatorBindings.getCommunicator(new InetSocketAddress("localhost", SERVER_PORT)));

		Communicator serverComm = CommunicatorBindings.getCommunicator(clientComm.localAddress());

		assertSame(serverComm, CommunicatorBindings.getCommunicator(clientComm.localAddress()));
		assertEquals(2, CommunicatorBindings.numberOfBindings());
	}

}
