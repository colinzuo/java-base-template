package com.example.basetemplate.util;

import java.io.IOException;

import com.example.basetemplate.util.jsonviews.PrivateView;
import com.example.basetemplate.util.jsonviews.PublicView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * 字符串工具类
 */
public class StrUtils {
	public final static ObjectMapper objectMapper = initMapper();

	public static ObjectMapper initMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		return mapper;
	}

	public static String jsonDump(Object obj) {
		String json = null;
		try {
			json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static String jsonDumpPrivateView(Object obj) {
		String json = null;
		try {
			json = objectMapper.writerWithView(PrivateView.class)
					.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static String jsonDumpPublicView(Object obj) {
		String json = null;
		try {
			json = objectMapper.writerWithView(PublicView.class)
					.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static String toPrettyFormat(String json) {
		ObjectMapper mapper = new ObjectMapper();

		try {
			Object jsonObject = mapper.readValue(json, Object.class);
			String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
			return prettyJson;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
