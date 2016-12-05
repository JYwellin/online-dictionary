import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search 
{
    public String Find_You_Dao(String find) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String word = find;
        word = word.replaceAll(" ","+");

        //根据查找单词构造查找地址
        HttpGet getWordMean = new HttpGet("http://dict.youdao.com/search?q=" + word + "&keyfrom=dict.index");
        CloseableHttpResponse response = httpClient.execute(getWordMean);//取得返回的网页源码

        String result = EntityUtils.toString(response.getEntity());
        response.close();
        //注意(?s)，意思是让'.'匹配换行符，默认情况下不匹配
        Pattern searchMeanPattern = Pattern.compile("(?s)<div class=\"trans-container\">.*?<ul>.*?</div>");
        Matcher m1 = searchMeanPattern.matcher(result); //m1是获取包含翻译的整个<div>的

        if (m1.find()) {
            String means = m1.group();//所有解释，包含网页标签
            Pattern getChinese = Pattern.compile("(?m)<li>(.*?)</li>"); //(?m)代表按行匹配
            Matcher m2 = getChinese.matcher(means);
            String res = "";
            while (m2.find()) {
                //在Java中(.*?)是第1组，所以用group(1)
                res =  res + m2.group(1)+"\n";
            }
            return res;
        } else {
            //System.out.println("未查找到释义.");
            String res = "";
            return res;
        }
    }
    
    public String Find_Baidu(String find) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String word = find;
        word = word.replaceAll(" ","+");

        //根据查找单词构造查找地址
        HttpGet getWordMean = new HttpGet("http://dict.baidu.com/s?wd=" + word + "&device=pc&from=home");
        CloseableHttpResponse response = httpClient.execute(getWordMean);//取得返回的网页源码

        String result = EntityUtils.toString(response.getEntity());
        response.close();
        //注意(?s)，意思是让'.'匹配换行符，默认情况下不匹配
        Pattern searchMeanPattern = Pattern.compile("(?s)<div class=\"en-content\">.*?<div>.*?</div>");
        Matcher m1 = searchMeanPattern.matcher(result); //m1是获取包含翻译的整个<div>的
        String res = "";
        if (m1.find()) {
            String means = m1.group();//所有解释，包含网页标签
            Pattern getChinese = Pattern.compile("<strong>(.*?)</strong><span>(.*?)</span>");
            Pattern getChinese_ = Pattern.compile("<span>(.*?)</span>");//(?m)代表按行匹配
            Matcher m2 = getChinese.matcher(means);
            Matcher m3 = getChinese_.matcher(means);

            while (m2.find()&&m3.find()) {
                //在Java中(.*?)是第1组，所以用group(1)
               res = res + m2.group(1) + m3.group(1) + "\n";
            }
            return res;
        } else {
            //System.out.println("未查找到释义.");
            return res;
        }
    }
    
    public String Find_Bing(String input) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();


        String word = input;
        word = word.replaceAll(" ","+");

        //根据查找单词构造查找地址
        HttpGet getWordMean = new HttpGet("http://cn.bing.com/dict/search?q=" + word);
        CloseableHttpResponse response = httpClient.execute(getWordMean);//取得返回的网页源码

        String result = EntityUtils.toString(response.getEntity());
        response.close();
        //注意(?s)，意思是让'.'匹配换行符，默认情况下不匹配
        Pattern searchMeanPattern = Pattern.compile("(?s)<meta name=\"description\".*?义.*?\"");
        Matcher m1 = searchMeanPattern.matcher(result); //m1是获取包含翻译的整个<div>的
        String result_t = "";
        if (m1.find()) {
            String means = m1.group();//所有解释，包含网页标签
            String[] res1 = means.split("义，");
            String[] res = res1[1].split("； ");
            for(int i = 0;i < res.length-1;i++)
            {
            	if(i == 0)
            	{
            		String[] te = res[0].split("]，");
            		result_t = result_t + te[te.length-1] + "\n";
            		continue;
            	}
            	else
            	{
            		result_t = result_t + res[i] + "\n";
            	}
            }
            return result_t;
            
        } else {
        	return result_t;
        }
    }
}
