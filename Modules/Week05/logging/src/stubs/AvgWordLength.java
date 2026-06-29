package stubs;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class AvgWordLength extends Configured implements Tool {
	@Override
	public int run(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("Usage: AvgWordLength <input path> <output path>");
			ToolRunner.printGenericCommandUsage(System.err);
			return -1;
		}
		// Retrieve background configurations passed implicitly by ToolRunner. //
		Configuration conf = this.getConf();
		// Instantiate a MapReduce Job. //
		Job job = Job.getInstance(conf, "Average Word Length with Logging");
		job.setJarByClass(AvgWordLength.class);
		
		job.setMapperClass(LetterMapper.class);
		job.setReducerClass(AverageReducer.class);
		
		//Declare output scheme of Mapper. //
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		// Declare Reducer output. //
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		return job.waitForCompletion(true) ? 0 : 1;
	}	
		public static void main(String[] args) throws Exception {
			System.out.println("Student Name: Truong Ngoc Gia Hieu");
			System.out.println("Student ID (Vietnam): SWS01217");
			System.out.println("Student ID (Australia): 105565520");
			// Delegate execution flow control to Toolrunner. //
			int exitCode = ToolRunner.run(new Configuration(), new AvgWordLength(), args);
			System.exit(exitCode);
		}
}
