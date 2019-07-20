import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DFSClient;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.URI;

public class LogToHdfs {
    public static void main(String[] args) throws Exception{
        File file =new File("D:\\cread_log_client.2019-04-08.log");

        try{

            BufferedReader br = new BufferedReader(new FileReader(file));
            String lineDta = "";

            Configuration conf = new Configuration();
            //FileSystem fs = FileSystem.get(new Configuration());
            FileSystem fs = FileSystem.get(new URI("hdfs://192.168.0.7:9000"), conf, "hadoop");
            //FileInputStream in = new FileInputStream(s);
            FSDataOutputStream out = fs.append(new Path("/em.csv"));
            //append    create

            while ((lineDta = br.readLine()) != null) {
                String[] names = {"PageNo","ActionNo","UserId","PageStart","ActionTime","Version"};
                String result[] = Logvalue.findStr(names,lineDta);
                System.out.println("lineDta:"+lineDta);
                String sin = "";
                if(result[0]!=null) {
                    String s = result[0].replace("\"", "") + "," + result[1].replace("\"", "") + ","
                            + result[2].replace("\"", "") + "," + result[3].replace("\"", "") + ","
                            + result[4].replace("\"", "") + "\r\n";
                    sin =s;

                    }
                System.out.println(sin);

//                Configuration conf = new Configuration();
//                //FileSystem fs = FileSystem.get(new Configuration());
//                FileSystem fs = FileSystem.get(new URI("hdfs://192.168.0.2:9000"), conf, "hadoop");
//                //FileInputStream in = new FileInputStream(s);
//                FSDataOutputStream out = fs.create(new Path("/work/em.csv"));
                InputStream in = new ByteArrayInputStream(sin.getBytes());

                byte[] buffer = sin.getBytes();
                out.write(buffer);


            }
            out.flush();

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
