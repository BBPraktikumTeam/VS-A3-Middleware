package mware_lib;

public class ExceptionMessage extends ReplyMessage {

	private String type;
	private String message;

	public ExceptionMessage(String msg) {
		super(Long.parseLong(msg.split(",")[1]));
		String[] str = msg.split(",");
		this.type = str[2];
		this.message = str[3];
	}

	public String type() {
		return type;
	}

	public String message() {
		return message;
	}

	public boolean exception() {
		return true;
	}

	public boolean result() {
		return false;
	}
	
	public void throwAsException() {
		Object o=null;
		try {
			final Class<?>[] CONSTRUCTOR_SIGNATURE = {
					Class.forName("java.lang.String") };
			final Object[] CONSTRUCTOR_ARGS = { message};
				o= Class.forName(type.substring(6)).getConstructor(CONSTRUCTOR_SIGNATURE).newInstance(CONSTRUCTOR_ARGS);

		} catch (Exception e) {
		}
		if(o==null || !(o instanceof RuntimeException)){
			throw new RuntimeException(message);
		}
		else
			throw ((RuntimeException) o);
	}

	@Override
	public String toString() {
		return Utilities.join(",", "exception", String.valueOf(messageId),
				type, message);
	}
}
