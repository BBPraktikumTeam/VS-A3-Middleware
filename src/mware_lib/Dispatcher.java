package mware_lib;

public class Dispatcher {
	// auf Client Seite
	public static void dispatch(String msg) {
		ReplyMessage replyMessage = null;
		if (msg.startsWith("result")) {
			replyMessage = new ResultMessage(msg);
		} else if (msg.startsWith("exception")) {
			replyMessage = new ExceptionMessage(msg);
		}
		ReplyMessageQueue.put(replyMessage);
		MessageSemaphores.release(replyMessage.messageId());
	}

	// auf Server Seite
	public static void dispatch(String msg, Communicator comm) {
		if (msg.startsWith("request")) {
			String[] resultLine = msg.split(",");
			String name = resultLine[2];
			Skeleton skel = SkeletonBindings.getSkeleton(name);
			skel.unmarshal(msg, comm);
		}
	}
}
