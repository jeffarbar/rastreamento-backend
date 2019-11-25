package br.com.send.vo;

public class ResponseVo {
	
	public ResponseVo(String msg) {
		super();
		this.msg = msg;
	}

	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
