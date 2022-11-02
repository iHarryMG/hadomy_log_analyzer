package net.daum.view.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.view.model.Category;
import net.daum.view.model.Date;
import net.daum.view.model.LogData;
import net.daum.view.model.Tab;
import net.daum.view.repository.LogRepository;

@Service("LogServiceImpl")
public class LogServiceImpl implements LogService {

	@Autowired
	private LogRepository logRepository;
	private LogServiceFactory logServiceFactory;

	public LogServiceImpl() {
		logServiceFactory = new LogServiceFactory();
	}
	
	public List<Category> getAllCategories() throws SQLException {
		List<Category> removeDuplicatedItemsFromCategoryList = logServiceFactory.removeDuplicatedItemsFromCategoryList(logRepository.getCategories());
		for(Category category : removeDuplicatedItemsFromCategoryList)
			System.out.println(category.getCategoryId() + " "+ category.getCategoryName());
		return removeDuplicatedItemsFromCategoryList;
	}

	
	public List<Tab> getAllTabs() throws SQLException {
		return  logServiceFactory.removeDuplicatedItemsFromTabList(logRepository.getTabs());
	}

	public List<Date> getAllDate() throws SQLException {
		return logServiceFactory.removeDuplicatedItemsFromDateList(logRepository.getDate());
	}

	public List<LogData> getAllList(LogData logData) throws SQLException {
		return getList(logData);
	}

	public List<LogData> getList(LogData logData) throws SQLException {
		String flag = logServiceFactory.recognizeRequest(logData);
		if(isMatch(flag, "CategoryTabDate"))
			return logRepository.getByCategoryTabDate(logData);
		else if(isMatch(flag, "Category"))
			return logRepository.getByCategory(logData);
		else if(isMatch(flag, "Tab"))
			return logRepository.getByTab(logData);
		else if(isMatch(flag, "Date"))
			return logRepository.getByDate(logData);
		else if(isMatch(flag, "CategoryTab"))
			return logRepository.getByCategoryTab(logData);
		else if(isMatch(flag, "CategoryDate"))
			return logRepository.getByCategoryDate(logData);
		else if(isMatch(flag, "TabDate"))
			return logRepository.getByTabDate(logData);
		else
			return null;
	}

	private boolean isMatch(String flag, String value) {
		return flag.equals(value);
	}

}
