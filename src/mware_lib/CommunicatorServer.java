package mware_lib;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CommunicatorServer {

	private final int SERVER_PORT = 6666;

	public CommunicatorServer() {
		int port = SERVER_PORT;
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			while (true) {
				Socket socket = serverSocket.accept();
				Communicator comm = new Communicator(socket);
				CommunicatorBindings.addCommunicator(comm);
				comm.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
