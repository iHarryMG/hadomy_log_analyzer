package net.daum.view.rdbworker;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileManager {

	private FileInputStream file;
	private DataInputStream data;
	private BufferedReader reader;
	private String logFileLine = null;
	private ArrayList<String> logFile;
	private File outFile;
	private FileWriter writer;

	public ArrayList<String> readFile(String path, String fileName) {
		try{
			openStreamToReadFile(path, fileName);
			readLogFile();
			closeStreams();
			return logFile;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	private void openStreamToReadFile(String path, String fileName) throws FileNotFoundException {
		logFile = new ArrayList<String>();
		file = new FileInputStream(path + fileName);
		data = new DataInputStream(file);
		reader = new BufferedReader(new InputStreamReader(data));
	}

	private void readLogFile() throws IOException {
		while((logFileLine  = reader.readLine()) != null){
			logFile.add(logFileLine);
		}
	}

	private void closeStreams() throws IOException {
		data.close();
		file.close();
		data.close();
		reader.close();
	}

	public void writeFile(ArrayList<String> logFile, String pathName, String fileName) {
		try{
			openWriterToWriteFile(pathName, fileName);
			writeLogFile(logFile);
			closeWriter();
			
			System.out.println("LogFile has been completely written.");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void writeLine(String line, String pathName, String fileName) {
		try{
			openWriterToWriteFile(pathName, fileName);
			writeline(line);
			closeWriter();
			
			System.out.println("LogFile has been completely written.");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	private void openWriterToWriteFile(String pathName, String fileName) throws IOException {
		outFile = new File(pathName + fileName);
		writer = new FileWriter(outFile);
	}

	private void writeLogFile(ArrayList<String> logFile) throws IOException {
		for(String line: logFile){
			writer.write(line);
			System.out.println(line);
		}
	}
	
	private void writeline(String line) throws IOException {
			writer.write(line);
			System.out.println(line);
	}

	private void closeWriter() throws IOException {
		writer.flush();
		writer.close();
	}

}
