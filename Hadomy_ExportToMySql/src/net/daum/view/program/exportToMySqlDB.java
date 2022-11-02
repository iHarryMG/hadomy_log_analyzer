package net.daum.view.program;

import java.io.IOException;
import net.daum.view.export.Exporter;
import net.daum.view.export.ExporterImpl;

public class exportToMySqlDB {

	private static Exporter exporter;

	public static void main(String[] args) throws IOException {
		exporter = new ExporterImpl();
		exporter.run(args);
	}

}
