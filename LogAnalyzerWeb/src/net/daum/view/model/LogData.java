package net.daum.view.model;

import java.util.List;

public class LogData {

	private String num;
	private String category;
	private List<String> categoryList;
	private String tab;
	private List<String> tabList;
	private String url;
	private String value;
	private String date;
	private List<String> dateList;

	public LogData() {
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public List<String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
	}

	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}
	
	public List<String> getTabList() {
		return tabList;
	}

	public void setTabList(List<String> tabList) {
		this.tabList = tabList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<String> getDateList() {
		return dateList;
	}

	public void setDateList(List<String> dateList) {
		this.dateList = dateList;
	}
	
}
