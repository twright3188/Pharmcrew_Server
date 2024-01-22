package kr.ant.kpa.pharmcrew.validation.exception;

public class InvalidEnumException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8593896099599007557L;
	
	private String parameterName;
	private String inputValue;
	private String[] enums;
	public InvalidEnumException(String parameterName, String inputValue, String[] enums) {
		super();
		this.parameterName = parameterName;
		this.inputValue = inputValue;
		this.enums = enums;
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
	public String[] getEnums() {
		return enums;
	}
	public void setEnums(String[] enums) {
		this.enums = enums;
	}

}
