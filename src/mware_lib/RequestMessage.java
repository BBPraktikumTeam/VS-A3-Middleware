package mware_lib;

import java.net.InetSocketAddress;

public final class RequestMessage {

	private final long messageId;
	private final String servant;
	private final InetSocketAddress server;
	private final String method;
	private final String param;

	public RequestMessage(long messageId, String servant,
			InetSocketAddress server, String method, String param) {
		this.messageId = messageId;
		this.servant = servant;
		this.server = server;
		this.method = method;
		this.param = param;
	}

	public long messageId() {
		return messageId;
	}

	public String servant() {
		return servant;
	}

	public InetSocketAddress server() {
		return server;
	}

	public String method() {
		return method;
	}

	public String param() {
		return param;
	}

	public String toString() {
		return Utilities.join(",", "request", String.valueOf(messageId),
				servant, method, param);
	}
}
