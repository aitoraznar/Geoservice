package com.geonames.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	public static String WORKING_DIRECTORY = System.getProperty("catalina.base");
	
	public static boolean isNumeric(String str) {
	  return str.matches("\\d+");
	}

	
	/**
	 * 
	 * @param code: 	Http or error code
	 * @param reason: 	The reason of valid or invalid response
	 * @param msg:		The message inherited to response
	 * @return
	 * @throws JsonProcessingException 
	 */
	public static String jr(Integer code, String reason, String msg) {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("code", code);
		response.put("reason", reason);
		response.put("msg", msg);
		try {
			return new ObjectMapper().writer().writeValueAsString(response);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return jr_error(reason, "Unexpected Error");
		}
	}
	
	/**
	 * 
	 * @param code: 	Http or error code
	 * @param reason: 	The reason of valid or invalid response
	 * @param json:		The json object inherited to response
	 * @return
	 * @throws JsonProcessingException 
	 */
	public static String jr(Integer code, String reason, Object obj) {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("code", code);
		response.put("reason", reason);
		response.put("obj", obj);
		try {
			return new ObjectMapper().writer().writeValueAsString(response);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return jr_error(reason, "Unexpected Error");
		}
	}
	
	/**
	 * 
	 * @param reason: 	The reason of valid or invalid response
	 * @param msg:		The message inherited to response
	 * @return
	 */
	public static String jr_error(String reason, String msg) {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("code", 500);
		response.put("reason", reason);
		response.put("msg", msg);
		try {
			return new ObjectMapper().writer().writeValueAsString(response);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "{\"code\":500,\"reason\":" + reason + ",\"msg\":" + msg + "}";
		}
	}
	
	/**
	 * Capitalize the first letter of a word
	 * @param s
	 * @return
	 */
	public static String capitalize(String s) {
		if (s == null) return "";
        if (s.length() == 0) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}
