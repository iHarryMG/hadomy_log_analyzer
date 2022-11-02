package net.daum.view.worker;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LogCleanerDaoImpl implements LogCleanerDao{

	private FileInputStream fileInputStream;
	private DataInputStream dataInputStream;
	private BufferedReader bufferedReader;
	private String inputFileLine = null;
	private ArrayList<String> inputFile;
	private File file;
	private FileWriter fileWriter;

	public ArrayList<String> readFile(String inputPath, String inputFileName) {
		try{
			openStreamToReadFile(inputPath, inputFileName);
			read();
			closeStreams();
			return inputFile;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	private void openStreamToReadFile(String inputPath, String inputFileName) throws FileNotFoundException {
		inputFile = new ArrayList<String>();
		fileInputStream = new FileInputStream(inputPath + inputFileName);
		dataInputStream = new DataInputStream(fileInputStream);
		bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
	}

	private void read() throws IOException {
		while((inputFileLine  = bufferedReader.readLine()) != null){
			inputFile.add(inputFileLine);
		}
	}

	private void closeStreams() throws IOException {
		dataInputStream.close();
		fileInputStream.close();
		dataInputStream.close();
		bufferedReader.close();
	}

	public void writeFile(ArrayList<String> outputFile, String outputPath, String outputFileName) {
		try{
			openWriterToWriteFile(outputPath, outputFileName);
			write(outputFile);
			closeWriter();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	private void openWriterToWriteFile(String outputPath, String outputFileName) throws IOException {
		file = new File(outputPath + outputFileName);
		fileWriter = new FileWriter(file);
	}

	private void write(ArrayList<String> outputFile) throws IOException {
		for(String line: outputFile){
			fileWriter.write(line);
		}
	}

	private void closeWriter() throws IOException {
		fileWriter.flush();
		fileWriter.close();
	}
}
