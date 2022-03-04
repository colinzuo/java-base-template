package com.example.basetemplate.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 基本响应格式，reqId用于标识请求，error只当遇到错误时才设置
 * traceRecords用于按需绑定处理过程中log，方便调试
 */
@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RpcResponse<T> {
	private String reqId;
	private ErrorInfo error;
	private List<String> traceRecords;
	private String detailMsg;
	private Long costTimeMs;

	private T data;

	public RpcResponse() {}
}
