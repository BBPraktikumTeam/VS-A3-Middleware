package mware_lib;

public class ResultMessage extends ReplyMessage {

	private final String value;

	ResultMessage(String msg) {
		super(Long.parseLong(msg.split(",")[1]));
		this.value = msg.split(",")[2];
	}

	public String value() {
		return value;
	}

	public boolean exception() {
		return false;
	}

	public boolean result() {
		return true;
	}
}
