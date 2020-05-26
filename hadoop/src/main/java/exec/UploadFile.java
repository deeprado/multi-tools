package exec;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class UploadFile {


    public static void main(String[] args) {
        try {
            // 1.配置
            Configuration conf = new Configuration();
            conf.set("fs.defaultFS", "hdfs://127.0.0.1:9000/");
            conf.set("dfs.client.use.datanode.hostname", "true");
            // 2.文件系统
            FileSystem fs = FileSystem.get(conf);
            //3. 创建文件夹
            String tempPath = "/partitioner";
            if (!fs.exists(new Path(tempPath))) {
                fs.mkdirs(new Path(tempPath));
            }
            // 4.上传文件
            fs.copyFromLocalFile(new Path("D:/word.txt"),
                    new Path(tempPath));
            System.out.println("========== put ===============");
//            if (!fs.exists(new Path("/wordcount"))) {
//                fs.mkdirs(new Path("/wordcount"));
//            }
//            System.out.println("======== mkdir =============== ");
//            fs.copyFromLocalFile(new Path("D:/word.json"),
//                    new Path("/aaa"));
//            System.out.println("========== rename ========= ");

//            fs.rename(new Path("/aaa/word.json"), new Path("/aaa/2.json"));

            // 5.下载文件
//            fs.copyToLocalFile(new Path("/aaa/2.json"), new Path("D:/"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}