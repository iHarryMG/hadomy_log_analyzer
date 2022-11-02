package net.daum.view.model;

import java.util.ArrayList;

public class ViewCategory {
	private ArrayList<String> categoryNames = null;
	private ArrayList<String> tabNames = null;
	private ArrayList<String> directories = null;
	private ArrayList<String> parameterNames = null;
	private ArrayList<String> parameterValues = null;
	
	public ViewCategory() {
		setCategoryNames(new ArrayList<String>());
		setTabNames(new ArrayList<String>());
		setDirectories(new ArrayList<String>());
		setParameterNames(new ArrayList<String>());
		setParameterValues(new ArrayList<String>());
	}

	public ArrayList<String> getCategoryNames() {
		return categoryNames;
	}

	public void setCategoryNames(ArrayList<String> categoryNames) {
		this.categoryNames = categoryNames;
	}

	public ArrayList<String> getTabNames() {
		return tabNames;
	}

	public void setTabNames(ArrayList<String> tabNames) {
		this.tabNames = tabNames;
	}

	public ArrayList<String> getDirectories() {
		return directories;
	}

	public void setDirectories(ArrayList<String> directories) {
		this.directories = directories;
	}

	public ArrayList<String> getParameterNames() {
		return parameterNames;
	}

	public void setParameterNames(ArrayList<String> parameterNames) {
		this.parameterNames = parameterNames;
	}

	public ArrayList<String> getParameterValues() {
		return parameterValues;
	}

	public void setParameterValues(ArrayList<String> parameterValues) {
		this.parameterValues = parameterValues;
	}
}
