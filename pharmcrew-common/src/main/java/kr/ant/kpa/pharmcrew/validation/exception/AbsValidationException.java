package kr.ant.kpa.pharmcrew.validation.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.bumdori.util.StringUtils;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;

public abstract class AbsValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2165752771450103999L;
	
	private VALIDATION validation;
	private Map<String, Object> dataMap;
	private String eid;
	public AbsValidationException(VALIDATION validation) {
		super();
		this.validation = validation;
		this.eid = UUID.randomUUID().toString();
	}
	
	public VALIDATION getValidation() {
		return validation;
	}
	
	public String getMessage() {
		return (String) getData("message");
	}
	public void setMessage(String message) {
		setData("message", message);
	}
	
	public Object getData(String key) {
		return dataMap == null ? null : dataMap.get(key);
	}
	public void setData(String key, Object value) {
		if (dataMap == null) {
			dataMap = new HashMap<String, Object>();
		}
		dataMap.put(key, value);
	}
	
	public String getEid() {
		return eid;
	}

	@Override
	public String toString() {
		return String.format("%d: %s", validation.getCode(), StringUtils.isEmpty(getMessage()) ? validation.getMessage(): getMessage());
	}

}
