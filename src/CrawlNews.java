import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrawlNews {
    public static void main(String[] args) {
        try{
            URL url = new URL("https://dantri.com.vn/");
            Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
            scanner.useDelimiter("\\Z");
            String content = scanner.next();
            scanner.close();
            content = content.replaceAll("\\r", "");
            content = content.replaceAll("\\n", "");
            String result = getContent(content);
            Pattern pattern = Pattern.compile("htm\">(.*?)</a>");
            Matcher matcher = pattern.matcher(result);
            while (matcher.find()){
                System.out.println(matcher.group(1).trim());
            }
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static String getContent(String string){
        String result = "";
        Pattern p1 = Pattern.compile("dt-list dt-list--link\">(.*?)</ul>");
        Matcher m1 = p1.matcher(string);
        while (m1.find()){
            result = m1.group(1);
        }
        return result;
    }
}
