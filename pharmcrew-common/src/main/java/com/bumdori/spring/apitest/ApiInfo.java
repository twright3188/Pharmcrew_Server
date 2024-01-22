package com.bumdori.spring.apitest;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.web.bind.annotation.RequestMethod;

public class ApiInfo {

	private String name;
	private String url;
	private RequestMethod method;
	private List<ParameterInfo> pathParameters = new ArrayList<ParameterInfo>();
	private List<ParameterInfo> parameters = new ArrayList<ParameterInfo>();
	private boolean needSession;
	private String description;
	private List<HistoryInfo> histories = new ArrayList<HistoryInfo>();
	private List<String> validations = new ArrayList<String>(); 
	private Map<String, Values<String, String, String, Object>> results = new TreeMap<String, Values<String, String, String, Object>>();
	private int resultDepth;
	private String error;
	
	private Method function;
	private Class<?> response;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public RequestMethod getMethod() {
		return method;
	}
	public void setMethod(RequestMethod method) {
		this.method = method;
	}
	public List<ParameterInfo> getPathParameters() {
		return pathParameters;
	}
//	public void setPathParameters(List<Parameter> pathParameters) {
//		this.pathParameters = pathParameters;
//	}
	public List<ParameterInfo> getParameters() {
		return parameters;
	}
//	public void setParameters(List<Parameter> parameters) {
//		this.parameters = parameters;
//	}
	public boolean isNeedSession() {
		return needSession;
	}
	public void setNeedSession(boolean needSession) {
		this.needSession = needSession;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<HistoryInfo> getHistories() {
		return histories;
	}
//	public void setHistories(List<HistoryInfo> histories) {
//		this.histories = histories;
//	}
	public List<String> getValidations() {
		return validations;
	}
//	public void setValidations(List<String> validations) {
//		this.validations = validations;
//	}
	public Map<String, Values<String, String, String, Object>> getResults() {
		return results;
	}
	//	public void setResults(Map<String, Pair<String, Object>> results) {
//		this.results = results;
//	}
	public int getResultDepth() {
		return resultDepth;
	}
	public void setResultDepth(int resultDepth) {
		this.resultDepth = resultDepth;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Method getFunction() {
		return function;
	}
	public void setFunction(Method function) {
		this.function = function;
	}
	public Class<?> getResponse() {
		return response;
	}
	public void setResponse(Class<?> response) {
		this.response = response;
	}
	@Override
	public String toString() {
		return "ApiInfo [name=" + name + ", url=" + url + ", method=" + method + ", pathParameters=" + pathParameters
				+ ", parameters=" + parameters + ", needSession=" + needSession + ", description=" + description
				+ ", histories=" + histories + ", results=" + results + ", resultDepth=" + resultDepth + ", error="
				+ error + ", function=" + function + ", response=" + response + "]";
	}
	
	public String makeMethodInForm() {
		if (method == null) {
			return "";
		}
		
		boolean file = false;
		
		if (parameters != null) {
			for (ParameterInfo param : parameters) {
				if (param.getType() == PARAMETER_TYPE.FILE) {
					file = true;
					break;
				}
			}
		}
		
		if (file) {
			return "method='post' enctype='multipart/form-data'";
		}
		
		switch (method) {
		case GET:
			return "method='get'";
		}
		
		return "method='post'"; 
	}
	
	public String makeMethodInHidden() {
		if (method == null) {
			return "";
		}
		
		switch (method) {
		case DELETE:
			return "<input name=\"_method\" type=\"hidden\" value=\"delete\" />";
		case PUT:
			return "<input name=\"_method\" type=\"hidden\" value=\"put\" />";
		}
		
		return "";
	}
	
	public String printResult() {
		StringBuffer sb = new StringBuffer();
		sb.append("<tr>");
		for (int i = 0; i < resultDepth; i++) {
			sb.append("<td>필드</td><td>타입</td><td>설명</td>");
		}
		sb.append("</tr>");
		printResult(sb, results, 1);
		return sb.toString();
	}
	
	private void printResult(StringBuffer sb, Map<String, Values<String, String, String, Object>> result, int blank) {
		int c = 0;
		Iterator<String> itr = result.keySet().iterator();
		while (itr.hasNext()) {
			String key = itr.next();
			Values<String, String, String, Object> value = result.get(key);
			if (blank <= 1 || c++ != 0) {
				sb.append("<tr>");
				for (int i = 1; i < blank; i++) {
					sb.append("<td></td><td></td><td></td>");
				}
			}
			sb.append("<td>");
			sb.append(key);
			sb.append("</td><td>");
			sb.append(value.t);
			sb.append("</td><td>");
			sb.append(value.d);
			sb.append("</td>");
			if (value.v != null) {
				printResult(sb, (Map<String, Values<String, String, String, Object>>) value.v, blank + 1);
			} else {
				for (int i = 0; i < resultDepth - blank; i++) {
					sb.append("<td></td><td></td><td></td>");
				}
			}
			sb.append("</tr>");
		}
	}
	
	public static class Values<T, D, H, V> {
		private T t;		// 타입
		private D d;		// 설명
		private H h;		// 
		private V v;
		public Values(T t, D d, H h, V v) {
			super();
			this.t = t;
			this.d = d;
			this.h = h;
			this.v = v;
		}
		public T getT() {
			return t;
		}
		public void setT(T t) {
			this.t = t;
		}
		public D getD() {
			return d;
		}
		public void setD(D d) {
			this.d = d;
		}
		public H getH() {
			return h;
		}
		public void setH(H h) {
			this.h = h;
		}
		public V getV() {
			return v;
		}
		public void setV(V v) {
			this.v = v;
		}
		@Override
		public String toString() {
			return "Values [t=" + t + ", d=" + d + ", h=" + h + ", v=" + v + "]";
		}
		
	}
	
}
