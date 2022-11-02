package net.daum.view.repository;

import java.sql.SQLException;
import java.util.List;

import net.daum.view.model.Category;
import net.daum.view.model.Date;
import net.daum.view.model.LogData;
import net.daum.view.model.Tab;

public interface LogRepository {

	List<Category> getCategories() throws SQLException;

	List<Tab> getTabs() throws SQLException;

	List<Date> getDate() throws SQLException;

	List<LogData> getByCategoryTabDate(LogData logData) throws SQLException;

	List<LogData> getByCategory(LogData logData);

	List<LogData> getByTab(LogData logData);

	List<LogData> getByDate(LogData logData);

	List<LogData> getByCategoryTab(LogData logData);

	List<LogData> getByCategoryDate(LogData logData);

	List<LogData> getByTabDate(LogData logData);
	
}
