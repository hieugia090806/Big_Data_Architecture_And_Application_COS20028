package stubs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class CombinerDriver extends Configured implements Tool {
	@Override
	public int run(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("Usage: CombinerDriver <input dir> <Output dir>");
			return -1;
		}
		
		Configuration conf = this.getConf();
		Job job = Job.getInstance(conf, "Optimized Word Count with Built-in Combiner API");
		
		job.setJarByClass(CombinerDriver.class);
		job.setMapperClass(WordMapper.class);
		// Call API. //
		job.setCombinerClass(SumReducer.class);
		job.setReducerClass(SumReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		return job.waitForCompletion(true) ? 0 : 1;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("Student Name: Truong Ngoc Gia Hieu");
		System.out.println("Student ID (VIetnam): SWS01217");
		System.out.println("Student ID (Australia): 105565520");
		
		int exitCode = ToolRunner.run(new Configuration(), new CombinerDriver(), args);
		System.exit(exitCode);
	}
}

