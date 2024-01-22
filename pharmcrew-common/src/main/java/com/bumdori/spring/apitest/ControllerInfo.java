package com.bumdori.spring.apitest;

import java.util.Map;
import java.util.TreeMap;

public class ControllerInfo {

	private String name;
	private Map<String, ApiInfo> apis = new TreeMap<String, ApiInfo>();
	private String error;
	private String className;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, ApiInfo> getApis() {
		return apis;
	}
	public void setApis(Map<String, ApiInfo> apis) {
		this.apis = apis;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	@Override
	public String toString() {
		return "Controller [name=" + name + ", apis=" + apis + ", error="
				+ error + "]";
	}
	
}
