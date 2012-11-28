package mware_lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public final class Communicator extends Thread {
	private final Socket socket;
	private final PrintWriter out;
	private final BufferedReader in;

	public Communicator(InetSocketAddress address) {
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			socket = new Socket(address.getAddress(), address.getPort());
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("mw: create new communicator (client)");
		this.socket = socket;
		this.out = out;
		this.in = in;
	}

	public Communicator(Socket socket) {
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("mw: create new communicator (server)");
		this.socket = socket;
		this.out = out;
		this.in = in;
	}

	public InetSocketAddress remoteAddress() {
		return new InetSocketAddress(socket.getInetAddress(), socket.getPort());
	}

	public InetSocketAddress localAddress() {
		return new InetSocketAddress(socket.getLocalAddress(),
				socket.getLocalPort());
	}

	public String toString() {
		return "Communicator: local:" + localAddress() + ", remote:"
				+ remoteAddress();
	}

	public void send(String msg) {
		System.out.println("sending: " + msg);
		out.println(msg);
	}

	public void run() {
		String inputLine = null;
		try {
			System.out.println("Communicator started");
			while (!socket.isClosed() && ((inputLine = in.readLine()) != null)) {
				System.out.println("receive: " + inputLine);
				if (inputLine.startsWith("request")) {
					Dispatcher.dispatch(inputLine, this);
				} else {
					Dispatcher.dispatch(inputLine);
				}
			}
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			// e.printStackTrace();
		} finally {
			System.out.println("Communicator stopped");
		}
	}
}
