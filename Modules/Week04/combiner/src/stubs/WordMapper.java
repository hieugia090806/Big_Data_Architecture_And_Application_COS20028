// Import package and libraries. //
package stubs;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	// Generate variables. //
	private final static IntWritable one = new IntWritable(1);
	private Text wordText = new Text();
	// map() function. //
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
		// Convert all letters to lower case. //
		String line = value.toString().toLowerCase();
		// Use tokenizer to split words based on special character. //
		StringTokenizer tokenizer = new StringTokenizer(line, "\t\n\r\f,.:;?!\"()'-");
		// While loop. //
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken().trim();
			if (token.length() > 0 && token.substring(0,1).matches("[a-zA-Z]")) {
				wordText.set(token);
				context.write(wordText, one);
			}
		}
	}
}
