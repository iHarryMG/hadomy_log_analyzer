package net.daum.view.hdfsworker;

import java.io.IOException;

import net.daum.view.model.OutputData;

public interface HdfsReader {

	OutputData readOutputData(String hdfsPath) throws IOException;

}
