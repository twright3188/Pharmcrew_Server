package com.bumdori.spring.apitest;

import com.bumdori.util.ClassUtils;
import com.bumdori.util.StringUtils;

public class ParameterInfo {

	private String name;
	private PARAMETER_TYPE type;
	private boolean require;
	private boolean array;
	private String description;
	private String error;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PARAMETER_TYPE getType() {
		return type;
	}
	public void setType(PARAMETER_TYPE type) {
		this.type = type;
	}
	public boolean isRequire() {
		return require;
	}
	public void setRequire(boolean require) {
		this.require = require;
	}
	public boolean isArray() {
		return array;
	}
	public void setArray(boolean array) {
		this.array = array;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "ParameterInfo [name=" + name + ", type=" + type + ", require="
				+ require + ", array=" + array + ", error=" + error + "]";
	}
	
	public String makeInputHtml() {
		if (type == null) {
			return "";
		}
		switch (type) {
		case UNKNOWN:
			return "알수 없는 타입";
		case FILE:
			return "<input name='" + name + "'type='file'>";
		case INTEGER:
		case LONG:
			return "<input name='" + name + "'type='number'>";
		case FLOAT:
		case DOUBLE:
		case STRING:
		default:
			return "<input name='" + name + "'type='text'>";
		}
		
	}
	
	public String makeSessionInputHtml(String defValue) {
		if (type == null) {
			return "";
		}
		
		String result = "";
		switch (type) {
		case UNKNOWN:
			result = "알수 없는 타입";
		case FILE:
			result = "<input name='" + name + "'type='file'";
		case INTEGER:
		case LONG:
			result = "<input name='" + name + "'type='number'";
		case FLOAT:
		case DOUBLE:
		case STRING:
		default:
			result = "<input name='" + name + "' type='text'";
		}
		
		if (StringUtils.isEmpty(defValue)) {
			result += ">";
		} else {
			result += " value='" + defValue + "' >";
		}
		
		return result;
	}
	
	public String getAndroidType() {

		switch (type) {
		case FILE:
			return "File";
		case INTEGER:
			return "Integer";
		case LONG:
			return "Long";
		case FLOAT:
			return "Float";
		case DOUBLE:
			return "Double";
		case STRING:
			return "String";
		default:
			return "INVALID";
		}
	}

	public String getAppleType()  {
			switch (type) {
		case FILE:
			return "NSInteger";
		case INTEGER:
			return "NSInteger";
		case LONG:
			return "NSInteger";
		case FLOAT:
			return "NSInteger";
		case DOUBLE:
			return "Double";
		case STRING:
			return "NSString *";
		}
			return "NSString *";
//		throw new Exception("Type 오류 : APPLE TYPE : " + type.name());
	}
	
	public String makeRequestField() {
		StringBuilder builder = new StringBuilder();
		builder.append("	private ");
		if (array) {
			builder.append("List<");
		}
		switch (type) {
		case FILE:
			builder.append("File");
			break;
		case INTEGER:
			builder.append("Integer");
			break;
		case LONG:
			builder.append("Long");
			break;
		case FLOAT:
			builder.append("Float");
			break;
		case DOUBLE:
			builder.append("Double");
			break;
		case STRING:
			builder.append("String");
			break;
		}
		if (array) {
			builder.append(">");
		}
		builder.append(" ");
		builder.append(ClassUtils.decapitalize(name));
		builder.append(";");
		
		return builder.toString();
	}
	
	public String makeUrlRequestSetter() {
		StringBuilder builder = new StringBuilder();
		builder.append("	public void set");
		builder.append(ClassUtils.capitalize(name));
//		builder.append("(String");
		builder.append("(");
		switch (type) {
		case FILE:
			builder.append("File");
			break;
		case INTEGER:
			builder.append("Integer");
			break;
		case LONG:
			builder.append("Long");
			break;
		case FLOAT:
			builder.append("Float");
			break;
		case DOUBLE:
			builder.append("Double");
			break;
		case STRING:
			builder.append("String");
			break;
		}
		builder.append(" value) {\r\n");
		builder.append("		setUriParam(\"");
		builder.append(name);
		builder.append("\", ");
//		builder.append("value");
		switch (type) {
		case FILE:
			builder.append("String.valueOf( value )");
			break;
		case INTEGER:
			builder.append("String.valueOf( value )");
			break;
		case LONG:
			builder.append("String.valueOf( value )");
			break;
		case FLOAT:
			builder.append("String.valueOf( value )");
			break;
		case DOUBLE:
			builder.append("String.valueOf( value )");
			break;
		case STRING:
			builder.append("value");
			break;
		}
		builder.append(");\r\n");
		builder.append("	}\r\n");
		
		return builder.toString();
	}
	
	public String makeRequestGetter() {
		StringBuilder builder = new StringBuilder();
		builder.append("	public ");
		if (array) {
			builder.append("List<");
		}
		switch (type) {
		case FILE:
			builder.append("File");
			break;
		case INTEGER:
			builder.append("Integer");
			break;
		case LONG:
			builder.append("Long");
			break;
		case FLOAT:
			builder.append("Float");
			break;
		case DOUBLE:
			builder.append("Double");
			break;
		case STRING:
			builder.append("String");
			break;
		}
		if (array) {
			builder.append(">");
		}
		builder.append(" get");
		builder.append(ClassUtils.capitalize(name));
		builder.append("() {\r\n");
		builder.append("		return ");
		builder.append(ClassUtils.decapitalize(name));
		builder.append(";\r\n");
		builder.append("	}\r\n");
		
		return builder.toString();
	}
	
	public String makeRequestSetter() {
		StringBuilder builder = new StringBuilder();
		builder.append("	public void ");
		if (array) {
			builder.append("add");
		} else {
			builder.append("set");
		}
		builder.append(ClassUtils.capitalize(name));
		builder.append("(");
		switch (type) {
		case FILE:
			builder.append("File");
			break;
		case INTEGER:
			builder.append("Integer");
			break;
		case LONG:
			builder.append("Long");
			break;
		case FLOAT:
			builder.append("Float");
			break;
		case DOUBLE:
			builder.append("Double");
			break;
		case STRING:
			builder.append("String");
			break;
		}
		builder.append(" value) {\r\n");
		if (array) {
			builder.append("		if (");
			builder.append(name);
			builder.append(" == null) {\r\n");
			builder.append("			" + name + " = new ArrayList<");
			switch (type) {
			case FILE:
				builder.append("File");
				break;
			case INTEGER:
				builder.append("Integer");
				break;
			case LONG:
				builder.append("Long");
				break;
			case FLOAT:
				builder.append("Float");
				break;
			case DOUBLE:
				builder.append("Double");
				break;
			case STRING:
				builder.append("String");
				break;
			}
			builder.append(">();\r\n");
			builder.append("		}\r\n");
			builder.append("		");
		} else {
			builder.append("		this.");
		}
		builder.append(name);
		if (array) {
			builder.append(".add(");
		} else {
			builder.append(" = ");
		}
		builder.append("value");
		if (array) {
			builder.append(")");
		}
		builder.append(";\r\n");
		builder.append("	}\r\n");
		
		return builder.toString();
	}
}
