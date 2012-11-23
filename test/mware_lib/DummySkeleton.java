package mware_lib;

final class DummySkeleton implements Skeleton {

	private long msgId;
	private String name;
	private String method;
	private String param;
	private Communicator comm = null;

	public DummySkeleton(String name) {
		this.msgId = 0;
		this.name = name;
		this.method = "DEFAULT_METHOD";
		this.param = "DEFAULT_PARAM";
	}

	public DummySkeleton() {
		this("SERVANT");
	}

	@Override
	public void unmarshal(String msg, Communicator comm) {
		String[] str = msg.split(",");
		msgId = Long.parseLong(str[1]);
		method = str[3];
		param = str[4];
		this.comm = comm;
	}

	public long msgId() {
		return msgId;
	}

	@Override
	public String name() {
		return name;
	}

	public String method() {
		return method;
	}

	public String param() {
		return param;
	}

	public Communicator comm() {
		return comm;
	}

}
