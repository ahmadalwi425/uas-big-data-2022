package org.example.avgflight;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import org.example.avgflight.flightAvgMapper;
import org.example.avgflight.flightAvgReducer;

import java.io.IOException;

public class App
{
    public static void main( String[] args )
    {
        JobConf conf = new JobConf(org.example.sumdistance.App.class);
        conf.setJobName("Sum Distance of Flight");

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);

        conf.setMapperClass(flightAvgMapper.class);
        conf.setCombinerClass(flightAvgReducer.class);
        conf.setReducerClass(flightAvgReducer.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        try {
            JobClient.runJob(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
