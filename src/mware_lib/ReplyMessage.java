package mware_lib;

public abstract class ReplyMessage {
	protected final long messageId;

	ReplyMessage(long messageId) {
		this.messageId = messageId;
	}

	public abstract boolean result();

	public abstract boolean exception();

	public long messageId() {
		return messageId;
	}

}
