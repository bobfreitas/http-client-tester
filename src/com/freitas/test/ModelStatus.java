package com.freitas.test;

public class ModelStatus {
	
	private boolean alive;
	private boolean success;
	private String errMsg;
	
	public ModelStatus(boolean finished, boolean success, String errMsg) {
		this.alive = finished;
		this.success = success;
		this.errMsg = errMsg;
	}

	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
