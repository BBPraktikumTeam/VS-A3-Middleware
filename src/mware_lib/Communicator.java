package mware_lib;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Map;

public final class Communicator {
	private static Map<InetSocketAddress, Socket> destinationSockets;
	
	public static void send(InetSocketAddress dest, long destObjectId, String msg) {
		
	}
	
	private Socket createSocket(InetSocketAddress dest) {
		return null;
	}
}
