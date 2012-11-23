package mware_lib;

import java.util.HashMap;
import java.util.Map;

public final class ReplyMessageQueue {
	private static Map<Long, ReplyMessage> messages = new HashMap<Long, ReplyMessage>();

	public synchronized static void put(ReplyMessage msg) {
		messages.put(msg.messageId(), msg);
	}

	public synchronized static ReplyMessage pop(long msgId) {
		ReplyMessage result = messages.get(msgId);
		messages.remove(msgId);
		return result;
	}
}
