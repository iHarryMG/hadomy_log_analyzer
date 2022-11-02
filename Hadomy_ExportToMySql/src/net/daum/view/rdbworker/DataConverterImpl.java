package net.daum.view.rdbworker;

import net.daum.view.model.OutputData;

public class DataConverterImpl implements DataConverter {
	
	private String[] values;
	private String[] data;

	@Override
	public OutputData convertToRdbData(OutputData outputData, String fieldSymbol, String valueFieldSymbol) {
		convertData(outputData, fieldSymbol, valueFieldSymbol);
		return outputData;
	}

	private void convertData(OutputData outputData, String fieldSymbol, String valueFieldSymbol) {
		for(int i = 0; i < outputData.getData().size(); i++){
				String line = outputData.getData().get(i);
				getData(line, fieldSymbol, valueFieldSymbol);
				setRdbData(outputData, data, values[1]);
		}		
	}

	private void getData(String line, String fieldSymbol, String valueFieldSymbol) {
		values = line.split(valueFieldSymbol);
		data = values[0].split(fieldSymbol);
	}
	
	
	private void setRdbData(OutputData outputData, String[] data, String value) {
		if(data.length > 2){
			outputData.getCategory().add(data[0]);
			outputData.getTab().add(data[1]);
			outputData.getUrl().add(data[2]);
			outputData.getValue().add(value);
			outputData.setHasData(true);
		}
	}
}
