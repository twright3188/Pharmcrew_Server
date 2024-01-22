package kr.ant.kpa.pharmcrew.resp;

import java.util.ArrayList;
import java.util.List;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;


/**
* 
 */
public class CommonListResp<T> extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	@Description("데이터")
	private List<T> data;

	public CommonListResp() {
		super();
	}
	public CommonListResp(VALIDATION validation) {
		super(validation);
	}
	
	public void setData(List<T> data) {
		if (data == null) {
			this.data = new ArrayList<T>();
		}
		this.data = data;
	}
	
	public List<T> getData() {
		return this.data;
	}


}
