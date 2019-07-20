import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logvalue {
    public static String[] findStr(String [] names,String str){
        String [] result = new String[names.length];
        for(int i=0;i<names.length;i++){
            String regex="\""+names[i]+"\":(.*?)[,|}]";  //
            //String PageNo="\"PageNo\":(.*?),";  //使用非贪婪模式
            Matcher matcher=Pattern.compile(regex).matcher(str);

            if(matcher.find()) {
                result[i]= matcher.group(1);

            }
        }
        return  result;

    }
}
