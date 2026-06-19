package stubs;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class LineCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	
	private final static IntWritable one = new IntWritable(1);
	private final static Text lineKey = new Text("Total Lines: ");
	
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		context.write(lineKey, one);
	}
}
