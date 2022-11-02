package net.daum.view.hdfsworker;


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import net.daum.view.model.OutputData;

public class HdfsReaderImpl implements HdfsReader {

	private FSDataInputStream fsDataInputStream;
	private String line;
	private FileSystem fileSystem;
	private FileStatus[] fileStatus;
	private OutputData outputData;

	@Override
	public OutputData readOutputData(String hdfsPath) throws IOException {
		return read(hdfsPath);
	}

	private OutputData read(String hdfsPath) throws IOException {
		setConfiguration(hdfsPath);
		return readDirectory();
	}

	private OutputData readDirectory() throws IOException {
		for(int i=0; i < fileStatus.length; i++){
			if(isDirectoryExist(i)){
				if(isFile(i)){
					openFileSystem(i);
					readData();
				}
			}
		}
		closeFileSystem();
		return outputData;
	}

	
	private void setConfiguration(String hdfsPath) throws IOException {
		fileSystem = FileSystem.get(new Configuration());
		fileStatus = fileSystem.listStatus(new Path(hdfsPath));
		outputData = new OutputData();
	}
	
	private boolean isDirectoryExist(int i) throws IOException {
		return fileSystem.exists(fileStatus[i].getPath());
	}
	
	private boolean isFile(int i) throws IOException {
		return fileSystem.isFile(fileStatus[i].getPath());
	}
	private void openFileSystem(int i) throws IOException {
		fsDataInputStream = fileSystem.open(fileStatus[i].getPath());
	}
	
	private void readData() throws IOException {
		while((line = fsDataInputStream.readLine()) != null)
			outputData.getData().add(line);
	}
	
	private void closeFileSystem() throws IOException {
		fsDataInputStream.close();
	}

}
