package mware_lib;

import java.util.HashMap;
import java.util.Map;

public final class ReplyMessages {
	private static Map<Long, ReplyMessage> messages = new HashMap<Long, ReplyMessage>();
	public static void put(ReplyMessage msg) {
		messages.put(msg.messageId(), msg);
	}
	
	public synchronized static ReplyMessage pop(long msgId) {
		ReplyMessage result = messages.get(msgId);
		messages.remove(msgId);
		return result;
	}
}
