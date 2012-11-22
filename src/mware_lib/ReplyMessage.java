package mware_lib;

public class ReplyMessage {

	private final long messageId;
//	private final String type;
	private final String value;

	ReplyMessage(String msg) {
		String[] resultLine = msg.split(",");
		this.messageId = Long.parseLong(resultLine[1]);
//		this.type = resultLine[2];
		this.value = resultLine[2];
	}

	public long messageId() {
		return messageId;
	}

//	public String type() {
//		return type;
//	}

	public String value() {
		return value;
	}

//	public String toString() {
//		return Utilities.join("", "request", String.valueOf(messageId),
//				servant, method, param);
//	}
}
