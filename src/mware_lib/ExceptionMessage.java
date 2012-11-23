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
	//	public Exception exception() {
//		Exception result = null;
//		result=Class.forName(type).;
//		return null;
//	}
}
