package kr.ant.kpa.pharmcrew.apitest;

import java.io.IOException;

import com.bumdori.spring.apitest.AbsApiMgr;
import com.bumdori.spring.generator.AbsGenerator;

public class ApiGenerator extends AbsGenerator {

	public ApiGenerator(String project, AbsApiMgr apiMgr) {
		super(project, apiMgr);
	}
	
	public static void main(String[] args) throws IOException {
		ApiGenerator generator = new ApiGenerator("pharmcrew", ApiMgr.getInstance());
//		generator.generateAndroid();
		generator.makeAppleReq();
	}
	
}
