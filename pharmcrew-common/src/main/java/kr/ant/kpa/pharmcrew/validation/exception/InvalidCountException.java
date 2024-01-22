package kr.ant.kpa.pharmcrew.validation.exception;

public class InvalidCountException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5319995762712695523L;
	
	private String parameterName;
	private Object[] inputValue;
	private Integer min;
	private Integer max;
	public InvalidCountException(String parameterName, Object[] inputValue, Integer min, Integer max) {
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
	public Object[] getInputValue() {
		return inputValue;
	}
	public void setInputValue(Object[] inputValue) {
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
