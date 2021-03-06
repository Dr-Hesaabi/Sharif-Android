package ir.dr_hesaabi.infa.webService;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Admin on 9/13/2017.
 */

public class MyWebClient {

        private String url  = "";

        public MyWebClient(String Url){
            url = Url;
        }

      public  String Post(String Route, List<NameValuePair> Values) throws ClientProtocolException, IOException {

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(new String(url + Route));
            httpPost.setEntity(new UrlEncodedFormEntity(Values));
            HttpResponse response = httpClient.execute(httpPost);
            return response.toString();

        }

       public String Get(String Route) throws ClientProtocolException, IOException {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(new String(url + Route ));
            HttpResponse response = httpClient.execute(httpGet);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        }

}
