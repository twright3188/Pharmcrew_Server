package kr.ant.kpa.pharmcrew.validation.exception;

public class InvalidRangeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5319995762712695523L;
	
	private String parameterName;
	private int inputValue;
	private Integer min;
	private Integer max;
	public InvalidRangeException(String parameterName, int inputValue, Integer min, Integer max) {
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
	public int getInputValue() {
		return inputValue;
	}
	public void setInputValue(int inputValue) {
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
