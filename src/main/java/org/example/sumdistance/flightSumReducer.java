package org.example.sumdistance;

import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

public class flightSumReducer extends MapReduceBase implements org.apache.hadoop.mapred.Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter)
            throws IOException {
        int sum = 0;

        while(values.hasNext()) {
            IntWritable currentAmount = values.next();
            sum += currentAmount.get();
        }
        output.collect(key, new IntWritable(sum));
    }
}
