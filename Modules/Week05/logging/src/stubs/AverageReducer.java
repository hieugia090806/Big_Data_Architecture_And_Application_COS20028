package stubs;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Reducer;
// Import logging libraries. //
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AverageReducer extends Reducer<Text, IntWritable, Text, DoubleWritable>{
	// Initialize the logger instance. //
	private static final Log LOG = LogFactory.getLog(AverageReducer.class);
	private DoubleWritable result = new DoubleWritable();
	
	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
		throws IOException, InterruptedException {
		// Set variable. //
		long sum = 0;
		long count = 0;
		// Accumulate word and oocurences. //
		for (IntWritable value : values) {
			sum += value.get();
			count++;
		}
		if (count > 0) {
			double average = (double) sum / count;
			result.set(average);
			
			context.write(key, result);
			// Log at info level //
			LOG.info("Reducer result - Letter:" + key.toString() + ", Average: " + average);
		}
	}

}
