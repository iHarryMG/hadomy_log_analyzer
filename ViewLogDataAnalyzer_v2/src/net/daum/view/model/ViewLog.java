package net.daum.view.model;

import java.util.ArrayList;

public class ViewLog {

	private String ip = null;
	private String dateTime = null;
	private String url = null;
	private String stateCode = null;
	private String inputLog = null;
	private ArrayList<String> cleanedLog = null;
	
	public ViewLog() {
		setCleanedLog(new ArrayList<String>());
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getInputLog() {
		return inputLog;
	}

	public void setInputLog(String inputLog) {
		this.inputLog = inputLog;
	}

	public ArrayList<String> getCleanedLog() {
		return cleanedLog;
	}

	public void setCleanedLog(ArrayList<String> cleanedLog) {
		this.cleanedLog = cleanedLog;
	}
}
