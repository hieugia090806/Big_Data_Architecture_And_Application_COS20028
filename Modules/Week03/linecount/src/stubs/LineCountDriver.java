package stubs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.reduce.IntSumReducer;

public class LineCountDriver {
	public static void main(String[] args) throws Exception {
		
		System.out.println("Student Name: Truong Ngoc Gia Hieu");
		System.out.println("Student ID (VIetName): SWS01217");
		System.out.println("Student ID (Swinburne): 105565520");

		
	    if (args.length != 2) {
	      System.out.printf(
	          "Usage: LineCountDriver <input dir> <output dir>\n");
	      System.exit(-1);
	    }
	    
	    
	    Configuration conf = new Configuration();
	    Job job = Job.getInstance(conf, "line Count");
	    
	    job.setJarByClass(LineCountDriver.class);
	    job.setMapperClass(LineCountMapper.class);
	    
	    job.setCombinerClass(IntSumReducer.class);
	    job.setReducerClass(IntSumReducer.class);
	    
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(IntWritable.class);
	    
	    FileInputFormat.setInputPaths(job, new Path(args[0]));
	    FileOutputFormat.setOutputPath(job, new Path(args[1]));
	    
	    System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}

