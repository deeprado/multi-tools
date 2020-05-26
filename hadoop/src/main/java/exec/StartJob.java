package exec;

import com.example.demo.sales.SalesCountryDriver;
import com.example.demo.sales.SalesCountryReducer;
import com.example.demo.sales.SalesCountryMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class StartJob {


    public static void main(String[] args) {
        try {
            // 1.配置
            Configuration conf = new Configuration();
//            conf.set("fs.defaultFS", "hdfs://hadoop1:9000/");
//            conf.set("dfs.client.use.datanode.hostname", "true");
            // 2.文件系统
            FileSystem fs = FileSystem.get(conf);
            //3. 创建文件夹
            String tempPath = "/user/lenvo/inputMapReduce";
            if (!fs.exists(new Path(tempPath))) {
                fs.mkdirs(new Path(tempPath));
            }
            // 4.上传文件
            fs.copyFromLocalFile(new Path("D:/Sales2014.csv"),
                    new Path(tempPath));

            // 5. 解析
            String outputPath = "/user/lenvo/mapreduce_output_sales";
            parse(tempPath, outputPath);

            // 6. 查看结果
            fs.listFiles(new Path(outputPath), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void parse(String inputPath, String outputPath) {
        JobClient my_client = new JobClient();
        // Create a configuration object for the job
        JobConf job_conf = new JobConf(SalesCountryDriver.class);

        // Set a name of the Job
        job_conf.setJobName("SalePerCountry");

        // Specify data type of output key and value
        job_conf.setOutputKeyClass(Text.class);
        job_conf.setOutputValueClass(IntWritable.class);

        // Specify names of Mapper and Reducer Class
        job_conf.setMapperClass(SalesCountryMapper.class);
        job_conf.setReducerClass(SalesCountryReducer.class);

        // Specify formats of the data type of Input and output
        job_conf.setInputFormat(TextInputFormat.class);
        job_conf.setOutputFormat(TextOutputFormat.class);

        // Set input and output directories using command line arguments,
        //arg[0] = name of input directory on HDFS, and arg[1] =  name of output directory to be created to store the output file.

        FileInputFormat.setInputPaths(job_conf, new Path(inputPath));
        FileOutputFormat.setOutputPath(job_conf, new Path(outputPath));

        my_client.setConf(job_conf);
        try {
            // Run the job
            JobClient.runJob(job_conf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}