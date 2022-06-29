package org.example.countflight;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import org.example.countflight.flightCountMapper;
import org.example.countflight.flightCountReducer;

import java.io.IOException;

public class App
{
    public static void main( String[] args )
    {
        JobConf conf = new JobConf(org.example.sumdistance.App.class);
        conf.setJobName("Sum Distance of Flight");

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);

        conf.setMapperClass(flightCountMapper.class);
        conf.setCombinerClass(flightCountReducer.class);
        conf.setReducerClass(flightCountReducer.class);

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