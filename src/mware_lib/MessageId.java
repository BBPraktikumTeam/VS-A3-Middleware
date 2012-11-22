package mware_lib;

public final class MessageId {
	private static long id=1;
	
	public synchronized static long getNewId() {
		return id++;
	}
}
