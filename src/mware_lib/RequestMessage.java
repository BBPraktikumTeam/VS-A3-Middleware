package mware_lib;

public final class RequestMessage {

	private final long messageId;
	private final String servant;
	private final String method;
	private final String param;

	public RequestMessage(long messageId, String servant, String method,
			String param) {
		this.messageId = messageId;
		this.servant = servant;
		this.method = method;
		this.param = param;
	}

	public long messageId() {
		return messageId;
	}

	public String servant() {
		return servant;
	}

	public String method() {
		return method;
	}

	public String param() {
		return param;
	}

	@Override
	public String toString() {
		return Utilities.join(",", "request", String.valueOf(messageId),
				servant, method, param);
	}
}
