package mware_lib;

import java.util.Map;
import java.util.concurrent.Semaphore;

public class MessageSemaphores {
	private static Map<Long, Semaphore> messageSemaphores;
	
	public static Semaphore create(long id) {
		Semaphore sem = new Semaphore(0);
		messageSemaphores.put(id, sem);
		return sem;
	}
	
	public static void release(long id) {
		Semaphore sem = messageSemaphores.get(id);
		sem.release();
		messageSemaphores.remove(id);
	}
}
