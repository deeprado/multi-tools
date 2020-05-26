package exec;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 *
 */
public class WordCountApp {

    /**
     * map 读取输入文件
     */
    public static class MyMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
        LongWritable one = new LongWritable(1);
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            // 转换字符串
            String line = value.toString();

            System.out.println("line : " + line);
            // 按空格，进行拆分
            String[] words = line.split(" ");
            //
            for(String str: words) {
                // 痛过上下文，输出统计结果
                context.write(new Text(str), one );
            }
        }


    }

    /**
     * reduce ： 归并操作
     */
    public static class MyReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
        @Override
        protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
            long sum = 0;
            for(LongWritable value: values) {
                // 求key出现的总次数
                sum += value.get();
            }
            // 统计结果输出
            context.write(key, new LongWritable(sum));

        }
    }

    /**
     * driver 封装MapReduce作业的所有信息
     * @param args
     */
    public static void main(String[] args) throws Exception{
        // 创建config
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://127.0.0.1:9000/");
        configuration.set("hadoop.tmp.dir", "D:\\Serv\\Hadoop\\tmp");
        configuration.set("dfs.client.use.datanode.hostname", "true");
        // 创建job
        Job job = Job.getInstance(configuration, "WordCount");
        //  设置job的处理类
        job.setJarByClass(WordCountApp.class);
        // 设置作业处理的输入路径
        FileInputFormat.setInputPaths(job, new Path("/wordcount/word.txt"));

        // 设置map相关参数
        job.setMapperClass(MyMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);
        job.setReducerClass(MyReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);


        //  设置作业处理输出路径
        // 2.文件系统
        FileSystem fs = FileSystem.get(configuration);
        String tempPath = "/wordcount/output1";
        if (fs.exists(new Path(tempPath))) {
            fs.delete(new Path(tempPath), true);
        }
        FileOutputFormat.setOutputPath(
                job, new Path(tempPath)
        );


        // 提交
        boolean res = job.waitForCompletion(true);
        System.out.println(
                res
        );
    }
}
