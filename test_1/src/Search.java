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

        //���ݲ��ҵ��ʹ�����ҵ�ַ
        HttpGet getWordMean = new HttpGet("http://dict.youdao.com/search?q=" + word + "&keyfrom=dict.index");
        CloseableHttpResponse response = httpClient.execute(getWordMean);//ȡ�÷��ص���ҳԴ��

        String result = EntityUtils.toString(response.getEntity());
        response.close();
        //ע��(?s)����˼����'.'ƥ�任�з���Ĭ������²�ƥ��
        Pattern searchMeanPattern = Pattern.compile("(?s)<div class=\"trans-container\">.*?<ul>.*?</div>");
        Matcher m1 = searchMeanPattern.matcher(result); //m1�ǻ�ȡ�������������<div>��

        if (m1.find()) {
            String means = m1.group();//���н��ͣ�������ҳ��ǩ
            Pattern getChinese = Pattern.compile("(?m)<li>(.*?)</li>"); //(?m)������ƥ��
            Matcher m2 = getChinese.matcher(means);
            String res = "";
            while (m2.find()) {
                //��Java��(.*?)�ǵ�1�飬������group(1)
                res =  res + m2.group(1)+"\n";
            }
            return res;
        } else {
            //System.out.println("δ���ҵ�����.");
            String res = "";
            return res;
        }
    }
    
    public String Find_Baidu(String find) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String word = find;
        word = word.replaceAll(" ","+");

        //���ݲ��ҵ��ʹ�����ҵ�ַ
        HttpGet getWordMean = new HttpGet("http://dict.baidu.com/s?wd=" + word + "&device=pc&from=home");
        CloseableHttpResponse response = httpClient.execute(getWordMean);//ȡ�÷��ص���ҳԴ��

        String result = EntityUtils.toString(response.getEntity());
        response.close();
        //ע��(?s)����˼����'.'ƥ�任�з���Ĭ������²�ƥ��
        Pattern searchMeanPattern = Pattern.compile("(?s)<div class=\"en-content\">.*?<div>.*?</div>");
        Matcher m1 = searchMeanPattern.matcher(result); //m1�ǻ�ȡ�������������<div>��
        String res = "";
        if (m1.find()) {
            String means = m1.group();//���н��ͣ�������ҳ��ǩ
            Pattern getChinese = Pattern.compile("<strong>(.*?)</strong><span>(.*?)</span>");
            Pattern getChinese_ = Pattern.compile("<span>(.*?)</span>");//(?m)������ƥ��
            Matcher m2 = getChinese.matcher(means);
            Matcher m3 = getChinese_.matcher(means);

            while (m2.find()&&m3.find()) {
                //��Java��(.*?)�ǵ�1�飬������group(1)
               res = res + m2.group(1) + m3.group(1) + "\n";
            }
            return res;
        } else {
            //System.out.println("δ���ҵ�����.");
            return res;
        }
    }
    
    public String Find_Bing(String input) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();


        String word = input;
        word = word.replaceAll(" ","+");

        //���ݲ��ҵ��ʹ�����ҵ�ַ
        HttpGet getWordMean = new HttpGet("http://cn.bing.com/dict/search?q=" + word);
        CloseableHttpResponse response = httpClient.execute(getWordMean);//ȡ�÷��ص���ҳԴ��

        String result = EntityUtils.toString(response.getEntity());
        response.close();
        //ע��(?s)����˼����'.'ƥ�任�з���Ĭ������²�ƥ��
        Pattern searchMeanPattern = Pattern.compile("(?s)<meta name=\"description\".*?��.*?\"");
        Matcher m1 = searchMeanPattern.matcher(result); //m1�ǻ�ȡ�������������<div>��
        String result_t = "";
        if (m1.find()) {
            String means = m1.group();//���н��ͣ�������ҳ��ǩ
            String[] res1 = means.split("�壬");
            String[] res = res1[1].split("�� ");
            for(int i = 0;i < res.length-1;i++)
            {
            	if(i == 0)
            	{
            		String[] te = res[0].split("]��");
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
