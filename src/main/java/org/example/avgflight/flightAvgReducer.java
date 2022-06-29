package org.example.avgflight;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

public class flightAvgReducer extends MapReduceBase implements org.apache.hadoop.mapred.Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter)
            throws IOException {
        int sum = 0;
        int total=0;

        while(values.hasNext()) {
            IntWritable currentAmount = values.next();
            sum ++;
            total += currentAmount.get();
        }
        float result = total/sum;
        output.collect(key, new IntWritable((int)(Math.round(result))));
    }
}

