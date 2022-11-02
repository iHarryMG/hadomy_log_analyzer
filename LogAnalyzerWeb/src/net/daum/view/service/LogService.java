package net.daum.view.service;

import java.sql.SQLException;
import java.util.List;

import net.daum.view.model.Category;
import net.daum.view.model.Date;
import net.daum.view.model.LogData;
import net.daum.view.model.Tab;


public interface LogService {

	List<Category> getAllCategories() throws SQLException;

	List<Tab> getAllTabs() throws SQLException;

	List<Date> getAllDate() throws SQLException;

	List<LogData> getAllList(LogData logData) throws SQLException;

}
