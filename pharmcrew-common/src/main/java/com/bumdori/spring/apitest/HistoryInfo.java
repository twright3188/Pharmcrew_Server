package com.bumdori.spring.apitest;

public class HistoryInfo {

	private String date;
	private String description;
	public HistoryInfo() {
		super();
	}
	public HistoryInfo(String date, String description) {
		super();
		this.date = date;
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
