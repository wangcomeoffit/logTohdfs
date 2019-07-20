import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.FileInputStream;

public class WriteTest {
    public static void main(String[] args) throws Exception{
        FileSystem fs = FileSystem.get(new Configuration());
        FileInputStream in = new FileInputStream("D:\\cread_log_client.2019-04-08.log");
        FSDataOutputStream out = fs.create(new Path("/work/em1.csv"));
        byte[] buffer = new byte[2048];
        while (true){
            int n = in.read(buffer);
            if(n == -1){
                break;
            }
            out.write(buffer,0,n);
            out.hflush();
        }
        out.close();
        in.close();
        System.out.println("ok....");



    }
}
