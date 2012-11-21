package mware_lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

final class NameServiceStub extends NameService {

	private final Socket socket;
	private PrintWriter out;
	private BufferedReader in;

	NameServiceStub(Socket socket) {
		this.socket = socket;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void rebind(Object servant, String name) {
		// String type=servant.getClass().toString();
		String type = "java.lang.Object";
		Class<?> clazz = servant.getClass();
		while (clazz.getSuperclass() != null) {
			if (clazz.toString().equals("class branch_access.Manager")) {
				type = "branch_access.Manager";
				break;
			} else if (clazz.toString().equals("class cash_access.Account")) {
				type = "cash_access.Account";
				break;
			}
			clazz = clazz.getSuperclass();
		}
		String host = socket.getLocalAddress().getHostAddress();
		String port = String.valueOf(socket.getLocalPort());
		System.out.println("try to rebind: " + name + "," + type + "," + host
				+ "," + port);
		out.println("rebind," + name + "," + type + "," + host + "," + port);
		try {
			System.out.println("ns: " + in.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object resolve(String name) {
		Object result = null;
		System.out.println("try to resolve: " + name);
		out.println("resolve," + name);
		String[] resultLine = null;
		try {
			resultLine = in.readLine().split(",");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (resultLine[0].equals("result")) {
			System.out.println("ns: " + resultLine[1] + "," + resultLine[2]
					+ "," + resultLine[3] + "," + resultLine[4]);
			result = createStub(resultLine[2],resultLine[3],Integer.parseInt(resultLine[4]));
		} else {
			System.out.println("ns: " + resultLine[0]);
		}
		return result;
	}

	static Object createStub(String type, String host, int port) {
		Object result = null;
		if (type.equals("branch_access.Manager")) {
			result = new branch_access.ManagerDummy();
		} else if (type.equals("cash_access.Account")) {
			result = new cash_access.AccountDummy();
		} else {
			result = new Object();
		}
		return result;
	}

}
