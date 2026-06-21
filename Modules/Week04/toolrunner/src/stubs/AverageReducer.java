package stubs;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class AverageReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	private IntWritable result = new IntWritable();
	
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		int sum = 0;
		int count = 0;
		for (IntWritable val : values) {
			sum += val.get();
			count++;
		}
		if (count>0) {
			int average = sum/count;
			result.set(average);
			context.write(key,  result);
		}
	}
}
