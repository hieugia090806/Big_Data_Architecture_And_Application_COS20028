package stubs;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;
// Import logging libraries. //
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LetterMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	// Initiate the logger instance for this class. //
	private static final Log LOG = LogFactory.getLog(LetterMapper.class);
	private Text firstLetter = new Text();
	private IntWritable wordLength = new IntWritable();
	
	@Override
	public void map(LongWritable key, Text value, Context context)
		throws IOException, InterruptedException {
		// Convert the inpur line into a standard string. //
		String line = value.toString();
		// Split line into individual tokens by using non-word characters as delimiters. //
		for (String word : line.split("\\W+")) {
			if (word.length() > 0) {
				// Extract first character and lower case it. //
				String firstChar = word.substring(0,1).toLowerCase();
				int length = word.length();
				// Process if first is alphabetical. //
				if (firstChar.matches("a-zA-Z")) {
					firstLetter.set(firstChar);
					wordLength.set(length);
					// Emit the Key-Value pair immediately to HDFS. //
					context.write(firstLetter, wordLength);
					// Log at debug level. //
					if (LOG.isDebugEnabled()) {
						LOG.debug("Mapper Processing - Key: " + firstChar + ", Length: " + wordLength);
					}
				}
				
			}
		}
	}
}
