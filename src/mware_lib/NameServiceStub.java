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
		CommunicatorLauncher.init();
		// String type=servant.getClass().toString();
		String type = Utilities.getTypeForObject(servant);
		String host = socket.getLocalAddress().getHostAddress();
		String port = String.valueOf(CommunicatorLauncher.serverPort());
		String message = Utilities.join(",", "rebind", name, type, host, port);
		System.out.println("try to rebind: " + message);
		out.println(message);
		String result = null;
		try {
			result = in.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("ns: " + result);
		if (result.equals("ok")) {
			Skeleton skel = Utilities.createSkeleton(name, servant);
			SkeletonBindings.addSkeleton(skel);
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
			result = Utilities.createProxy(resultLine[1], resultLine[2],
					resultLine[3], Integer.parseInt(resultLine[4]));
		} else {
			System.out.println("ns: " + resultLine[0]);
			//TODO: Exception werfen
		}
		return result;
	}

}
