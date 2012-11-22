package mware_lib;

public class Dispatcher {
	// auf Client Seite
	public static void dispatch(String msg) {
		if (msg.startsWith("reply")) {
			ReplyMessage replyMessage = new ReplyMessage(msg);
			ReplyMessages.put(replyMessage);
			MessageSemaphores.release(replyMessage.messageId());
		} else {
			// exception werfen / weitergeben
		}
	}

	public static void dispatch(String msg, Communicator comm) {
		if (msg.startsWith("request")) {
			String[] resultLine = msg.split(",");
			String name = resultLine[1];
			Skeleton skel = LocalSkeletons.getLocalSkeleton(name);
			skel.unmarshal(msg, comm);

		} else {
			// exception werfen / weitergeben
		}
	}
}
