package net.daum.view.worker;

import java.util.ArrayList;

public interface LogCleanerDao {

	ArrayList<String> readFile(String inputPath, String inputFileName);

	void writeFile(ArrayList<String> logFile, String outputPath, String outputFileName);

}
