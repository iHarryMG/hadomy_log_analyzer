package net.daum.view.program;

import net.daum.view.driver.DriverExecuter;
import net.daum.view.driver.DriverExecuterImpl;

public class grep {

	private static DriverExecuter driverExecuter;

	public static void main(String[] args) throws Exception {		
		driverExecuter = new DriverExecuterImpl();
		driverExecuter.execute(args);
	}
}
