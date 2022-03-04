package com.example.basetemplate.api;

/**
 * 错误码定义
 * number between 1 and 4999 is for error code agreed by different modules
 * errors seen by end user should fall into this range
 * number between 5000 and 9999 is for module specific error code
 */
public enum CommonErrorCode implements IErrorCode {
	RES_UNAUTHORIZED(401, "RES_UNAUTHORIZED"),
	RES_FORBIDDEN(403, "RES_FORBIDDEN");

	private final int code;
	private final String message;

	CommonErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
