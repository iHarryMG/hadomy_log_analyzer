package net.daum.view.worker;

import net.daum.view.model.ViewLog;
import org.apache.hadoop.io.Text;

public class ParserImpl implements Parser {

	private String key;
	private ConfigurationReader configurationReader;
	private LogCleaner logCleaner;
	private Text word;

	public void declareVariables(){
		word = new Text();
		logCleaner = new LogCleanerImpl();
		configurationReader = new ConfigurationReaderImpl();
	}
	
	@Override
	public Text parseData(ViewLog viewLog, String option) {
		declareVariables();
		getKeyForWhatLineToBeExtracted();
		makeCleanValue(viewLog);
		return parse(viewLog, option);
	}
	
	private void getKeyForWhatLineToBeExtracted() {
		key = configurationReader.readConfiguration("analyzer.xml", "analyzer.parse.key");
	}

	private void makeCleanValue(ViewLog viewLog) {
		viewLog = logCleaner.cleanValue(viewLog);
	}
	
	private Text parse(ViewLog viewLog, String option) {
		if(isKeyEquals(viewLog)){
			if(isEquals(option, "ip"))
				word.set(viewLog.getIp());
			else if(isEquals(option, "time"))
				word.set(viewLog.getDateTime());
			else if(isEquals(option, "url"))
				word.set(viewLog.getUrl());
		}
		return word;
	}

	private boolean isKeyEquals(ViewLog viewLog) {
		return key.equals(viewLog.getStateCode());
	}
	
	private boolean isEquals(String option, String key) {
		return key.equals(option);
	}
}
