package net.daum.view.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.daum.view.model.Category;
import net.daum.view.model.Date;
import net.daum.view.model.LogData;
import net.daum.view.model.Tab;

public class LogServiceFactory {

	public String recognizeRequest(LogData logData) throws SQLException {
		if((logData.getCategory() != "") && (logData.getTab() != "") && (logData.getDate() != ""))
			return "CategoryTabDate";
		else if((logData.getCategory() != "") && (logData.getTab() == "") && (logData.getDate() == ""))
			return "Category";
		else if((logData.getCategory() == "") && (logData.getTab() != "") && (logData.getDate() == ""))
			return "Tab";
		else if((logData.getCategory() == "") && (logData.getTab() == "") && (logData.getDate() != ""))
			return "Date";
		else if((logData.getCategory() != "") && (logData.getTab() != "") && (logData.getDate() == ""))
			return "CategoryTab";
		else if((logData.getCategory() != "") && (logData.getTab() == "") && (logData.getDate() != ""))
			return "CategoryDate";
		else if((logData.getCategory() == "") && (logData.getTab() != "") && (logData.getDate() != ""))
			return "TabDate";
		else
			return "";
	}
	
	public List<Category> removeDuplicatedItemsFromCategoryList(List<Category> categories) {
		List<Category> resultCategories = new ArrayList<Category>();
		resultCategories.add(categories.get(0));
		String categoryId = categories.get(0).getCategoryId();
		
		int size = categories.size();
		for(int i = 1; i < size; i++){
			if(categories.get(i).getCategoryId().equals(categoryId) == false){
				categoryId = categories.get(i).getCategoryId();
				resultCategories.add(categories.get(i));
			}
		}
		return resultCategories;
	}
	
	public List<Tab> removeDuplicatedItemsFromTabList(List<Tab> tabs) {
		List<Tab> resultTabs = new ArrayList<Tab>();
		resultTabs.add(tabs.get(0));
		String tabId = tabs.get(0).getTabId();
		
		int size = tabs.size();
		for(int i = 1; i < size; i++){
			if(tabs.get(i).getTabId().equals(tabId) == false){
				tabId = tabs.get(i).getTabId();
				resultTabs.add(tabs.get(i));
			}
		}
		return resultTabs;
	}
	
	public  List<Date> removeDuplicatedItemsFromDateList(List<Date> dateList) {
		List<Date> resultDate = new ArrayList<Date>();
		resultDate.add(dateList.get(0));
		String dateId = dateList.get(0).getDateId();
		
		int size = dateList.size();
		for(int i = 1; i < size; i++){
			if(dateList.get(i).getDateId().equals(dateId) == false){
				dateId = dateList.get(i).getDateId();
				resultDate.add(dateList.get(i));
			}
		}
		return resultDate;
	}

}
