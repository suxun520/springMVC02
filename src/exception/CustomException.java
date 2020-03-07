package exception;
/*
 * 预期异常
 */

public class CustomException extends Exception {
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public CustomException(String msg) {
		this.msg = msg;
	}

}
