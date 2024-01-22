package kr.ant.kpa.pharmcrew.validation.exception;

public class InvalidRegexException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8593896099599007557L;
	
	private String parameterName;
	private String inputValue;
	private String regex;
	public InvalidRegexException(String parameterName, String inputValue, String regex) {
		super();
		this.parameterName = parameterName;
		this.inputValue = inputValue;
		this.regex = regex;
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
	public String getRegex() {
		return regex;
	}
	public void setRegex(String regex) {
		this.regex = regex;
	}

}
