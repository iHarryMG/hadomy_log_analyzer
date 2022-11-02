package net.daum.view.repository;

import java.sql.SQLException;
import java.util.List;

import net.daum.view.model.Category;
import net.daum.view.model.Date;
import net.daum.view.model.LogData;
import net.daum.view.model.Tab;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("logRepositoryImpl")
public class LogRepositoryImpl extends SqlSessionDaoSupport implements LogRepository {

	@SuppressWarnings("unchecked")
	public List<Category> getCategories() throws SQLException {
		return getSqlSession().selectList("log.selectAllCategory");
	}

	@SuppressWarnings("unchecked")
	public List<Tab> getTabs() throws SQLException {
		return getSqlSession().selectList("log.selectAllTab");
	}

	@SuppressWarnings("unchecked")
	public List<Date> getDate() throws SQLException {
		return getSqlSession().selectList("log.selectAllDate");
	}
	
	@SuppressWarnings("unchecked")
	public List<LogData> getByCategoryTabDate(LogData logData) throws SQLException {
		return getSqlSession().selectList("log.selectByCategoryTabDate", logData);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LogData> getByCategory(LogData logData) {
		return getSqlSession().selectList("log.selectByCategory", logData);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LogData> getByTab(LogData logData) {
		return getSqlSession().selectList("log.selectByTab", logData);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LogData> getByDate(LogData logData) {
		return getSqlSession().selectList("log.selectByDate", logData);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LogData> getByCategoryTab(LogData logData) {
		return getSqlSession().selectList("log.selectByCategoryTab", logData);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LogData> getByCategoryDate(LogData logData) {
		return getSqlSession().selectList("log.selectByCategoryDate", logData);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LogData> getByTabDate(LogData logData) {
		return getSqlSession().selectList("log.selectByTabDate", logData);
	}

}
