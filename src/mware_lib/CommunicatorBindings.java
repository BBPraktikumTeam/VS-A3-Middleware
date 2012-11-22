package mware_lib;

import java.net.InetSocketAddress;
import java.util.Map;

public final class CommunicatorBindings {
	private static Map<InetSocketAddress, Communicator> communicators;

	public static Communicator getCommunicator(InetSocketAddress dest) {
		Communicator comm = communicators.get(dest);
		if (comm == null) {
			comm = new Communicator(dest);
			communicators.put(dest, comm);
			comm.start();
		}
		return comm;
	}

	static void addCommunicator(Communicator comm) {
		communicators.put(comm.socketAddress(), comm);
	}
}
