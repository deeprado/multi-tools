package spring;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 使用spring hadoop 访问 hdfs
 */
public class SpringHadoopApp {

    private ApplicationContext ctx;
    private FileSystem fileSystem;

    @Before
    public void setUp() {
        ctx = new ClassPathXmlApplicationContext("beans.xml");
        fileSystem = (FileSystem)ctx.getBean("hadoopFileSystem");
    }

    @After
    public void testDown() {
        ctx = null;
    }

    /**
     * 创建目录
     * @throws Exception
     */
    @Test
    public void testMkdir() throws Exception {
        fileSystem.mkdirs(new Path("/spring"));
    }

    @Test
    public void testUploadFile() throws Exception {
        // 4.上传文件
        String tempPath = "/spring/";
        fileSystem.copyFromLocalFile(new Path("D:/word.txt"),
                new Path(tempPath));
    }
}
