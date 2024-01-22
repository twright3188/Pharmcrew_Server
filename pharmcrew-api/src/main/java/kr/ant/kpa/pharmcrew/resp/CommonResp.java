package kr.ant.kpa.pharmcrew.resp;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;


/**
* 
 */
public class CommonResp<T> extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	@Description("데이터")
	private T data;

	public CommonResp() {
		super();
	}
	public CommonResp(VALIDATION validation) {
		super(validation);
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public T getData() {
		return this.data;
	}


}
