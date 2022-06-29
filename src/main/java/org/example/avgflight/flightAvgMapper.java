package org.example.avgflight;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;


public class flightAvgMapper  extends MapReduceBase implements org.apache.hadoop.mapred.Mapper<LongWritable, Text, Text, IntWritable> {
    private IntWritable WritableValue = new IntWritable(0);
    private Text tailNum = new Text();
    @Override
    public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
        String line = value.toString();

        String[] split = line.split(",");
        String type = split[8].trim();
        System.out.println(type);
        String distanceSplit = split[4];
        System.out.println(distanceSplit);
        this.tailNum.set(type);
        this.WritableValue.set(Integer.parseInt(distanceSplit));
        output.collect(this.tailNum, this.WritableValue);
    }
}
