package kr.ant.kpa.pharmcrew.apitest;

import com.bumdori.spring.apitest.AbsApiMgr;

public class ApiMgr extends AbsApiMgr {
	
	private static ApiMgr inst = null;
	
	public static final ApiMgr getInstance() {
		if (inst == null) {
			inst = new ApiMgr("kr.ant.kpa.pharmcrew.controller");
		}
		return inst;
	}

	private ApiMgr(String basePackage) {
		super(basePackage);
	}

}
