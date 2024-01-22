package kr.ant.kpa.pharmcrew.validation.exception;

public class InvalidLengthException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5319995762712695523L;
	
	private String parameterName;
	private String inputValue;
	private Integer min;
	private Integer max;
	public InvalidLengthException(String parameterName, String inputValue, Integer min, Integer max) {
		super();
		this.parameterName = parameterName;
		this.inputValue = inputValue;
		this.min = min;
		this.max = max;
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
	public Integer getMin() {
		return min;
	}
	public void setMin(Integer min) {
		this.min = min;
	}
	public Integer getMax() {
		return max;
	}
	public void setMax(Integer max) {
		this.max = max;
	}

}
