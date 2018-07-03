package com.ft.api.utils;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import org.codehaus.jackson.map.DeserializationConfig;

public final class CommonUtils {
	
	private CommonUtils(){
		
	}
	/** The Constant EMPTYSTRING. */
	public static final String EMPTYSTRING = "";

	/** The Constant NULLVALUE. */
	public static final String NULLVALUE = "null";
	
	/** The object mapper. */
	private static ObjectMapper objectMapper = new ObjectMapper();

	/** The json factory. */
	private static JsonFactory jsonFactory = new JsonFactory();
	
	/**
	 * This method is called to check the given string is null.
	 * 
	 * @param pStr
	 *            the str
	 * @return boolean
	 */
	public static boolean isNullorEmpty(String pStr) {
		// check for the empty string
		if (pStr == null || EMPTYSTRING.equals(pStr.trim())
				|| pStr.trim().length() < 1
				|| NULLVALUE.equalsIgnoreCase(pStr.trim())) {
			// return true incase of string is null
			return true;
		}
		// return false incase of string is not null
		return false;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static boolean isAlpha(String name) {
	    return name.matches("[a-zA-Z]+");
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static boolean isNumeric(String name) {
	    return name.matches("^?[0-9]+?$");
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static boolean isAlphaNumeric(String name) {
	    return name.matches("[a-zA-Z0-9]+");
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static boolean isAlphaNumericWithSplChars(String name) {
	    return name.matches("[a-zA-Z0-9-.()%&s_*]+");
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static boolean isEmail(String name) {
	    //return name.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		return true;
	}
	
	/**
	 * 
	 * @param pojo
	 * @return
	 * @throws IOException
	 */
	public static String toJson(Object pojo) throws IOException {
		StringWriter sw = new StringWriter();
		// creating the jsonGenerator
		JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(sw);
		jsonGenerator.useDefaultPrettyPrinter();
		// write the value of pojo into json generator
		objectMapper.writeValue(jsonGenerator, pojo);
		// return the json string
		return sw.toString();
	}
	
	/**
	 * 
	 * @param jsonAsString
	 * @param pojoClass
	 * @return
	 * @throws IOException
	 */
	public static <T> Object toPojo(String jsonAsString, Class<T> pojoClass)
			throws IOException {
		// configuring the columns to object mapper
		objectMapper
		.configure(
				DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		if(jsonAsString!=null){
			byte ptext[] = jsonAsString.getBytes("UTF-8"); 
			String jsonValue = new String(ptext, "UTF-8");
			return objectMapper.readValue(jsonValue, pojoClass);
		}

		// Return the pojo for the given json string
		return objectMapper.readValue(jsonAsString, pojoClass);

	}
	
	/**
	 * This method is used to get the current time in milliseconds.
	 *
	 * @return long startTime
	 */
	public static long startTime() {
		return System.currentTimeMillis();
	}
	
	/**
	 * This method is used to get the time taken in milliseconds.
	 *
	 * @param startTime
	 *            the start time
	 * @return long timeTaken
	 */
	public static long timeTaken(long startTime) {
		return System.currentTimeMillis() - startTime;
	}
	/**
	 * 
	 * @param httpStatus
	 * @param message
	 * @param errorCode
	 * @return ResponseEntity<Object>
	 */
	/*public static ResponseEntity<Object> buildResponse(HttpStatus httpStatus, String message, String errorCode){
		ExceptionResponse excResponse = new ExceptionResponse();
		excResponse.setTimestamp(System.currentTimeMillis());
		excResponse.setStatus(httpStatus.value());
		excResponse.setError(httpStatus.name());
		excResponse.setException(SENDGRIDEXCEPTION.getValue());
		excResponse.setMessage(message);
        excResponse.setError_code(errorCode);
        excResponse.setPath(PATH.getValue());
        return new ResponseEntity<>(excResponse, httpStatus);
	}*/
}
