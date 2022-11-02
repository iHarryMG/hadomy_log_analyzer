package net.daum.view.mapred;

import java.io.IOException;
import java.util.StringTokenizer;

import net.daum.view.model.ViewLog;
import net.daum.view.worker.Parser;
import net.daum.view.worker.ParserImpl;
import net.daum.view.worker.ConfigurationReader;
import net.daum.view.worker.ConfigurationReaderImpl;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.util.Tool;

public class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable>{

	private final static IntWritable one = new IntWritable(1);
	private Text word;
	private ViewLog viewLog;
	private Parser parser;
	private String option = null;
	
	public void configure(JobConf job){
		option = job.get("option");
	}

	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, 
			Reporter reporter) throws IOException {
		 declareVariables(value);
		 parseDataFromLine(viewLog, option);
		 exportIntermediateData(output);
	}

	private void declareVariables(Text value) {
		word = new Text();
		viewLog = new ViewLog();
		viewLog.setInputLog(value.toString());
		parser = new ParserImpl();
	}

	private void parseDataFromLine(ViewLog viewLog, String option) {
		word = parser.parseData(viewLog, option);
	}

	private void exportIntermediateData(OutputCollector<Text, IntWritable> output) throws IOException {
		if(isNotNull())
			output.collect(word, one);
	}

	private boolean isNotNull() {
		return word != null;
	}
}
