package net.daum.view.driver;

import net.daum.view.model.AnalyzerOptions;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class MapRedDriverImpl extends Configured implements Tool, MapRedDriver{
	
	private String option;
	private AnalyzerOptions analyzerOptions;
	
	public MapRedDriverImpl(AnalyzerOptions analyzerOptions) {
		this.analyzerOptions = analyzerOptions;
	}
		
	public void runDriver(String[] args) throws Exception {
		int res = ToolRunner.run(new Configuration(), new MapRedDriverImpl(this.analyzerOptions), args);
		System.exit(res);
	}
	
	public int run(String[] args) throws Exception {
		JobConf conf = declareJob();
		declareOutputTypes(conf);
		declareMapperReducer(conf);
		declareIOFormat(conf);
		setExtractDataOption(this.analyzerOptions, conf);
		setIOPath(conf, args);
		runJob(conf);
		return 0;
	}

	private JobConf declareJob() {
		JobConf conf = new JobConf(getConf(), net.daum.view.driver.MapRedDriverImpl.class);
		conf.setJobName("v.daum.log.analyzer");
		return conf;
	}
	
	private void declareOutputTypes(JobConf conf) {
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
	}
	
	private void declareMapperReducer(JobConf conf) {
		conf.setMapperClass(net.daum.view.mapred.Map.class);
		conf.setCombinerClass(net.daum.view.mapred.Reduce.class);
		conf.setReducerClass(net.daum.view.mapred.Reduce.class);
	}
	
	private void declareIOFormat(JobConf conf) {
		conf.setInputFormat(TextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);
	}
	
	private void setExtractDataOption(AnalyzerOptions analyzerOptions, JobConf conf) {
		if(analyzerOptions.isIp() == true)
			option = "ip";
		else if(analyzerOptions.isTime() == true)
			option = "time";
		else if(analyzerOptions.isUrl() == true)
			option = "url";
		else
			option = "-1";
		
		conf.set("option", option);
	}
	
	private void setIOPath(JobConf conf, String[] args) {
		FileInputFormat.setInputPaths(conf, new Path(args[1]));
		FileOutputFormat.setOutputPath(conf, new Path(args[2]));
	}

	private void runJob(JobConf conf) {
		try {
			JobClient.runJob(conf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
