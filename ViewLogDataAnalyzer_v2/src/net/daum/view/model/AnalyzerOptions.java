package net.daum.view.model;

public class AnalyzerOptions {
	
	private String optionsIntro = "\n" +
			"The v.daum log file analyzer program requires options for what data to be extracted. \n" +
			"Usage: grep -[OPTIONS] input output \n" +
			"where OPTIONS is one of: \n" +
			"\t ip \t\t\t extract IP \n" +
			"\t time \t extract pick time \n" +
			"\t url \t\t\t extract URL \n" +
			"where input/output is: \n" +
			"\t input \t\t\t path of a source file \n" +
			"\t output \t\t destination path of the source file \n";

	private boolean ip = false;
	private boolean time = false;
	private boolean url = false;
	private boolean webBrowser = false;

	public AnalyzerOptions() {
	}

	public String getOptionsIntro() {
		return optionsIntro;
	}

	public boolean isIp() {
		return ip;
	}

	public void setIp(boolean ip) {
		this.ip = ip;
	}

	public boolean isTime() {
		return time;
	}

	public void setTime(boolean time) {
		this.time = time;
	}

	public boolean isUrl() {
		return url;
	}

	public void setUrl(boolean url) {
		this.url = url;
	}

	public boolean isWebBrowser() {
		return webBrowser;
	}

	public void setWebBrowser(boolean webBrowser) {
		this.webBrowser = webBrowser;
	}
}
