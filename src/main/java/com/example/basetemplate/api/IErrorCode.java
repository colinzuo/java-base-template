package com.example.basetemplate.api;

/**
 * 定义接口从而各模块可以分别定义满足规范的错误码
 */
public interface IErrorCode {
	int getCode();

	String getMessage();
}
