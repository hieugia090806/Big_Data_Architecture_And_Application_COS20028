// Import package and libraries. //
package stubs;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.conf.Configuration;

public class LetterMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	// Import global class variable. //
	private boolean caseSensitive = false;
	// Reusable objects. //
	private Text firstLetter = new Text();
	private IntWritable wordLength = new IntWritable();
	// setup() class //
	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		Configuration conf = context.getConfiguration();
		this.caseSensitive = conf.getBoolean("caseSensitive", false);
	}
	// map() class. //
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		if (!caseSensitive) {
			line = line.toLowerCase();
		}
		
		StringTokenizer tokenizer = new StringTokenizer(line, " \t\n\r\f,.:;?!\"()'-");
		while (tokenizer.hasMoreTokens()) {
			String word = tokenizer.nextToken();
			
			if (word.length() > 0) {
				String firstChar = word.substring(0, 1);
				firstLetter.set(firstChar);
				
				wordLength.set(word.length());
				context.write(firstLetter, wordLength);
			}
		}
		
		
	}
}
