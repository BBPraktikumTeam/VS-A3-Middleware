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
		this.socket = socket;
		this.out = out;
		this.in = in;
	}

	public InetSocketAddress socketAddress() {
		return new InetSocketAddress(socket.getInetAddress(), socket.getPort());
	}

	public void send(String msg) {
		out.println(msg);
	}

	public void run() {
		String inputLine = null;
		try {
			System.out.println("Communicator started");
			while (!socket.isClosed() && ((inputLine = in.readLine()) != null)) {
				Dispatcher.dispatch(inputLine, this);
			}
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
//			e.printStackTrace();
		} finally {
			System.out.println("Communicator stopped");
		}
	}
}
