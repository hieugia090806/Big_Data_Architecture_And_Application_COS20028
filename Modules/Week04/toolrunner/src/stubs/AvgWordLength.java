// Import package and libraries. //
package stubs;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
// Extend Configured and implements Tool interface. //
public class AvgWordLength extends Configured implements Tool {
	@Override
	public int run(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("Usage: AvgWordLength <input path> <output path>");
			return -1;
		}
		// Retrieve Configuration initialized by Toolrunner. //
		Configuration conf = this.getConf();
		Job job = Job.getInstance(conf, "Average Word Length:");
		job.setJarByClass(AvgWordLength.class);
		// Assign Mapper and Reducer class. //
		job.setMapperClass(LetterMapper.class);
		job.setReducerClass(AverageReducer.class);
		// Set output Key-Value type. //
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		// Paths Input and Output. //
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		// Job execution. //
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
