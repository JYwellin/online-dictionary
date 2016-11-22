import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
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
            while (m2.find()) {
                //��Java��(.*?)�ǵ�1�飬������group(1)
                return m2.group(1);
            }
            return null;
        } else {
            //System.out.println("δ���ҵ�����.");
            String res = "";
            return res;
        }
    }
}
