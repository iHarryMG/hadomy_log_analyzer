package net.daum.view.driver;

import net.daum.view.model.AnalyzerOptions;

public class DriverExecuterImpl implements DriverExecuter {

	private MapRedDriver mapRedDriver;
	private AnalyzerOptions analyzerOptions;

	public void execute(String[] args) throws Exception {
		analyzerOptions = new AnalyzerOptions();
		
		if(isOptionProvided(analyzerOptions, args)){
			mapRedDriver = new MapRedDriverImpl(analyzerOptions);
			mapRedDriver.runDriver(args);
		}
		else{
			notifyOptions(analyzerOptions);
		}
	}

	private boolean isOptionProvided(AnalyzerOptions analyzerOptions, String[] args) {
		boolean argsResult = false;
		
		for(int i = 0; i < args.length; i++){
			if(isNull(args, i)){
				argsResult = false;
				break;
			}
			else{
				argsResult = setOption(analyzerOptions, args);
			}
		}
		return argsResult;
	}

	private boolean setOption(AnalyzerOptions analyzerOptions, String[] args) {
		if(isEquals(args, "-ip")){
			analyzerOptions.setIp(true);
			return true;
		}
		else if(isEquals(args, "-time")){
			analyzerOptions.setTime(true);
			return true;
		}
		else if(isEquals(args, "-url")){
			analyzerOptions.setUrl(true);
			return true;
		}
		else
			return false;
	}
	
	private void notifyOptions(AnalyzerOptions analyzerOptions) {
		System.out.println(analyzerOptions.getOptionsIntro());		
	}
	
	private boolean isNull(String[] args, int i) {
		return args[i] == null;
	}
	
	private boolean isEquals(String[] args, String option) {
		return args[0].equals(option);
	}
}
