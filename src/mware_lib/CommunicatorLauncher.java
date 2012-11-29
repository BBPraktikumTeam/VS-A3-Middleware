package mware_lib;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CommunicatorLauncher extends Thread {

	private final static int DEFAULT_SERVER_PORT = 6667;
	private final ServerSocket serverSocket;
	private static int serverPort;
	private static CommunicatorLauncher cl;

	public CommunicatorLauncher() {
		this(DEFAULT_SERVER_PORT);
	}

	public CommunicatorLauncher(int port) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		serverPort = port;
		this.serverSocket = serverSocket;
	}

	@Override
	public void run() {
		try {
			while (true) {
				Socket socket = serverSocket.accept();
				Communicator comm = new Communicator(socket);
				CommunicatorBindings.addCommunicator(comm);
				comm.setDaemon(true);
				comm.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void init() {
		if (cl == null) {
			cl = new CommunicatorLauncher();
			cl.setDaemon(true);
			cl.start();
		}
	}

	public static int serverPort() {
		return serverPort;
	}
}
