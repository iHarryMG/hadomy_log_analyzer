package net.daum.view.rdbworker;

import net.daum.view.model.OutputData;

public interface RdbWriter {

	void writeOutputData(String query, OutputData outputData);

}
