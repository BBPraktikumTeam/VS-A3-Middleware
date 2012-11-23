package mware_lib;

public interface Skeleton {
	public String name();
	public void unmarshal(String msg, Communicator comm);
}
