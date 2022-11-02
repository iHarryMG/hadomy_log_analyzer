package net.daum.view.worker;

import net.daum.view.model.ViewLog;

import org.apache.hadoop.io.Text;

public interface Parser {

	Text parseData(ViewLog viewLog, String option);

}
