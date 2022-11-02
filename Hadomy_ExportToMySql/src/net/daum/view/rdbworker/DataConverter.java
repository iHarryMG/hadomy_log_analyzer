package net.daum.view.rdbworker;

import net.daum.view.model.OutputData;

public interface DataConverter {

	OutputData convertToRdbData(OutputData outputData, String fieldSymbol, String valueFieldSymbol);

}
