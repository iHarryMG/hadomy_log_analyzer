package net.daum.view.export;

import java.io.IOException;

import net.daum.view.hdfsworker.HdfsReader;
import net.daum.view.hdfsworker.HdfsReaderImpl;
import net.daum.view.model.OutputData;
import net.daum.view.rdbworker.DataConverter;
import net.daum.view.rdbworker.DataConverterImpl;
import net.daum.view.rdbworker.RdbWriter;
import net.daum.view.rdbworker.RdbWriterImpl;

public class ExporterImpl implements Exporter {

	private OutputData outputData;
	private HdfsReader hdfsReader;
	private RdbWriter rdbWriter;
	private DataConverter dataConverter;
	private String hdfsPath;
	private String fieldSymbol;
	private String valueFieldSymbol;
	private String query = "";
	
	@Override
	public void run(String[] args) throws IOException {
		declareVariables();
		arrangeArgs(args);
		outputData = hdfsReader.readOutputData(hdfsPath);
		outputData = dataConverter.convertToRdbData(outputData, fieldSymbol, valueFieldSymbol);
		rdbWriter.writeOutputData(query, outputData);
	}
	
	private void declareVariables() {
		hdfsReader = new HdfsReaderImpl();
		dataConverter = new DataConverterImpl();
		rdbWriter = new RdbWriterImpl();
	}

	private void arrangeArgs(String[] args) {
		hdfsPath = args[0];
		fieldSymbol = args[args.length - 2];
		valueFieldSymbol = args[args.length - 1];
		for(int i = 1; i < (args.length - 2); i++)
			query += " " + args[i];
	}
}
