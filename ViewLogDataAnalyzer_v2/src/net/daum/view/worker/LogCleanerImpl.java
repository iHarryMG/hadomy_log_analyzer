package net.daum.view.worker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import net.daum.view.model.ViewCategory;
import net.daum.view.model.ViewLog;

import org.apache.hadoop.io.Text;

public class LogCleanerImpl implements LogCleaner {

	private ArrayList<String> patterns;
	private Set<String> patternsToSkip;
	private LogCleanerDao logCleanerDao;
	private String cleanValue = null;	
	private StringTokenizer stringTokenizer;
	private Text word;
	private ConfigurationReader configurationReader;
	private CatergoryConfigurationReader categoryConfigurationReader;
	private ViewCategory viewCategory;
	private String comparableDirectory = null;
	private String comparableParameter = null;
	private String categoryName = null;
	private String[] categoryNames = null;
	private String inputUrl;
	
	private void declareVariables() {
		patternsToSkip = new HashSet<String>();
		logCleanerDao = new LogCleanerDaoImpl();
		configurationReader = new ConfigurationReaderImpl();
		categoryConfigurationReader = new CatergoryConfigurationReaderImpl(); 
		viewCategory = new ViewCategory();
		word = new Text();
	}
	
	@Override
	public ViewLog cleanValue(ViewLog viewLog) {
		return clean(viewLog);
	}

	private ViewLog clean(ViewLog viewLog) {
		declareVariables();
		getSkipPatterns();
		replacePatterns(viewLog);
		arrangeLine(viewLog);
		if(isRearrenged(viewLog))
			setModelViewLog(viewLog);
		return viewLog;
	}

	private boolean isRearrenged(ViewLog viewLog) {
		return rearrangeLine(viewLog) == true;
	}

	private void getSkipPatterns() {
		patterns = logCleanerDao.readFile(System.getenv("HADOOP_HOME") + "/conf/" , "patterns.txt");
		for(int i = 0; i < patterns.size(); i++){
			String patternLine = patterns.get(i);
			patternsToSkip.add(patternLine);
		}
	}

	private void replacePatterns(ViewLog viewLog) {
		cleanValue = viewLog.getInputLog();
		for(String patternLine : patternsToSkip){
			cleanValue = cleanValue.replaceAll(patternLine, " ");
		}
		viewLog.setInputLog(cleanValue);
	}
	
	private void arrangeLine(ViewLog viewLog) {
		stringTokenizer = new StringTokenizer(viewLog.getInputLog());
		while (stringTokenizer.hasMoreTokens()) {
			word.set(stringTokenizer.nextToken());
			if(isContains("HTTP")){
				getStateCodeField(viewLog);
				break;
			} else
				getField(viewLog);
		}
	}

	private boolean isContains(String string) {
		return word.toString().contains(string);
	}
	
	private void getStateCodeField(ViewLog viewLog) {
		word.set(stringTokenizer.nextToken());
		getField(viewLog);
	}

	private void getField(ViewLog viewLog) {
		viewLog.getCleanedLog().add(word.toString());
	}
	

	private boolean rearrangeLine(ViewLog viewLog) {
		getCategoryNames();
		getInputUrl(viewLog);
		for(String category : categoryNames ){
			if(isExist(category)){
				for(int i = 0; i < viewCategory.getTabNames().size(); i++){
					getDirectoryValue(i);			
					if(isParameterNull(i)){
						setNullParameter(i);
					}
					else{
						setParameter(i);
					}
					if(isMatch(inputUrl, comparableDirectory, comparableParameter)){
						return setCleanedLogUrl(viewLog, category,  viewCategory.getTabNames().get(i), comparableDirectory, comparableParameter);
					}
				}
			}
			resetViewCategory(viewCategory);
		}
		return false;
	}

	private void getDirectoryValue(int i) {
		comparableDirectory = viewCategory.getDirectories().get(i) + "?";
	}

	private void getInputUrl(ViewLog viewLog) {
		inputUrl = viewLog.getCleanedLog().get(3);
	}

	private void setParameter(int i) {
		comparableParameter = viewCategory.getParameterNames().get(i) + "=";
		comparableParameter += viewCategory.getParameterValues().get(i);
	}

	private void setNullParameter(int i) {
		comparableParameter = viewCategory.getParameterNames().get(i);
		comparableParameter += viewCategory.getParameterValues().get(i);
	}

	private boolean isExist(String category) {
		return categoryConfigurationReader.readCategoryConfiguration(viewCategory, "singlecategory.xml", category) == true;
	}

	private boolean isParameterNull(int i) {
		return viewCategory.getParameterNames().get(i).equals("") && viewCategory.getParameterValues().get(i).equals("");
	}

	private void getCategoryNames() {
		categoryName = configurationReader.readConfiguration("analyzer.xml", "analyzer.view.category");
		categoryNames  = categoryName.split(",");
	}

	private boolean setCleanedLogUrl(ViewLog viewLog, String categoryName, String tabName, String directory, String parameter) {
		String ipSet = categoryName + "-" + tabName + "-" + viewLog.getCleanedLog().get(0);
		String dateTimeSet = categoryName + "-" + tabName + "-" + viewLog.getCleanedLog().get(2);
		String urlSet = categoryName + "-" + tabName + "-" + directory + "&" + parameter;
		viewLog.getCleanedLog().set(0, ipSet);
		viewLog.getCleanedLog().set(2, dateTimeSet);
		viewLog.getCleanedLog().set(3, urlSet);
		return true;
	}

	private boolean isMatch(String url, String comparableDirectory,
			String comparableParameter) {
		boolean directoryMatch = url.contains(comparableDirectory);
		boolean parameterMatch = url.contains(comparableParameter);
		return directoryMatch && parameterMatch;
	}
	
	private void resetViewCategory(ViewCategory viewCategory) {
		viewCategory.getCategoryNames().clear();
		viewCategory.getTabNames().clear();
		viewCategory.getDirectories().clear();
		viewCategory.getParameterNames().clear();
		viewCategory.getParameterValues().clear();
	}

	private void setModelViewLog(ViewLog viewLog) {
		viewLog.setIp(viewLog.getCleanedLog().get(0));
		viewLog.setDateTime(viewLog.getCleanedLog().get(2));
		viewLog.setUrl(viewLog.getCleanedLog().get(3));
		viewLog.setStateCode(viewLog.getCleanedLog().get(4));
	}
}
