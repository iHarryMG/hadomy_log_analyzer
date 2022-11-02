package net.daum.view.model;

import java.util.ArrayList;

public class OutputData {

	private ArrayList<String> data = null;
	private ArrayList<String> category = null;
	private ArrayList<String> tab = null;
	private ArrayList<String> url = null;
	private ArrayList<String> value = null;
	private boolean hasData;
	
	public OutputData() {
		setData(new ArrayList<String>());
		setCategory(new ArrayList<String>());
		setTab(new ArrayList<String>());
		setUrl(new ArrayList<String>());
		setValue(new ArrayList<String>());
	}

	public ArrayList<String> getData() {
		return data;
	}

	public void setData(ArrayList<String> data) {
		this.data = data;
	}

	public ArrayList<String> getCategory() {
		return category;
	}

	public void setCategory(ArrayList<String> category) {
		this.category = category;
	}

	public ArrayList<String> getTab() {
		return tab;
	}

	public void setTab(ArrayList<String> tab) {
		this.tab = tab;
	}

	public ArrayList<String> getUrl() {
		return url;
	}

	public void setUrl(ArrayList<String> url) {
		this.url = url;
	}

	public ArrayList<String> getValue() {
		return value;
	}

	public void setValue(ArrayList<String> value) {
		this.value = value;
	}

	public boolean isHasData() {
		return hasData;
	}

	public void setHasData(boolean hasData) {
		this.hasData = hasData;
	}

}
