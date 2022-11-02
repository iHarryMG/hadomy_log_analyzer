package net.daum.view.rdbworker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import net.daum.view.model.OutputData;

public class RdbWriterImpl implements RdbWriter {

	private ConfigurationReader configurationReader;
	private String driverClass;
	private String jdbcUrl;
	private String jdbcUserName;
	private String jdbcUserPassword;
	private PreparedStatement preparedStatement;
	private Class driver_class;
	private Connection connection;

	@Override
	public void writeOutputData(String query, OutputData outputData) {
		write(query, outputData);
	}

	private void write(String query, OutputData outputData) {
		try{
			establishMySqlDbConnection();
			for(int i = 0; i < outputData.getCategory().size(); i++){
				insertOutputData(query, outputData, i);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	private void establishMySqlDbConnection() throws ClassNotFoundException,
			SQLException {
		configurationReader = new ConfigurationReaderImpl();
		driverClass = configurationReader.readConfiguration("mysqlConfiguration.xml", "mysql.jdbc.driver.class");
		driver_class = Class.forName(driverClass);
		jdbcUrl = configurationReader.readConfiguration("mysqlConfiguration.xml", "mysql.jdbc.url");
		jdbcUserName = configurationReader.readConfiguration("mysqlConfiguration.xml", "mysql.jdbc.username");
		jdbcUserPassword = configurationReader.readConfiguration("mysqlConfiguration.xml", "mysql.jdbc.password");
		connection = DriverManager.getConnection(jdbcUrl, jdbcUserName, jdbcUserPassword);
	}
	
	private void insertOutputData(String query, OutputData outputData, int i)
			throws SQLException {
		if(isHas(outputData)){
			save(query, outputData, i);
		}
	}
	
	private boolean isHas(OutputData outputData) {
		return outputData.isHasData() == true;
	}

	private void save(String query, OutputData outputData, int i)
			throws SQLException {
		preparedStatement = connection.prepareStatement(query + " values(" + getFieldCount(query) + ")");
		preparedStatement.setString(1, outputData.getCategory().get(i));
		preparedStatement.setString(2, outputData.getTab().get(i));
		preparedStatement.setString(3, outputData.getUrl().get(i));
		preparedStatement.setString(4, outputData.getValue().get(i));
		preparedStatement.setString(5, getYesterdayDate().toString());
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

	private java.sql.Date getYesterdayDate() {
		Calendar today = Calendar.getInstance();
		today.add(Calendar.DATE, -1);
		java.sql.Date yesterday = new java.sql.Date(today.getTimeInMillis());
		return yesterday;
	}

	private String getFieldCount(String query) {
		String values = "?";
		String addvalue = ",?";
		String[] temp = split(query, "\\(");
		temp = split(temp[1], "\\)");
		int fieldCount = temp[0].split(",").length;
		for(int i = 0; i < fieldCount-1; i ++)
			values  += addvalue;
		return values;
	}

	private String[] split(String query, String regex) {
		return query.split(regex);
	}
}
