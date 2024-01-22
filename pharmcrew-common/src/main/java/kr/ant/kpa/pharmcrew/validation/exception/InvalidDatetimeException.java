package kr.ant.kpa.pharmcrew.validation.exception;

public class InvalidDatetimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8593896099599007557L;
	
	private String parameterName;
	private String inputValue;
	private String format;
	public InvalidDatetimeException(String parameterName, String inputValue, String format) {
		super();
		this.parameterName = parameterName;
		this.inputValue = inputValue;
		this.format = format;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public String getInputValue() {
		return inputValue;
	}
	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}

}
