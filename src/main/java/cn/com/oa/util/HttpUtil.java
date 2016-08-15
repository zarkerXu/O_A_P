package cn.com.oa.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * Class description
 *
 *
 * @version        1.0.0, 16/03/31
 * @author         zlh
 */
public class HttpUtil {
    /**
     * Method description post
     *
     *
     * @param url
     * @param httpEntity
     * @param json
     *
     * @return String 
     */
    public static String post(String url, HttpEntity httpEntity, boolean usetoken) {

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        if (usetoken) {
        	httpPost.setHeader("Authorization","Bearer "+Const.HX_ACCESS_TOKEN);
        }
        HttpClient httpClient = new DefaultHttpClient();
        int code=0;
        try {

            // urlEncodedFormEntity = new UrlEncodedFormEntity(formParams,
            // "UTF-8");
            httpPost.setEntity(httpEntity);

            HttpResponse response = httpClient.execute(httpPost);
            code     = response.getStatusLine().getStatusCode();

            System.out.println("code" + code);

            if (code == 200) {
                String entity = EntityUtils.toString(response.getEntity(), "UTF-8");

                System.out.println("entity" + entity);

                return entity;
            }else{
            	String entity = EntityUtils.toString(response.getEntity(), "UTF-8");

                System.out.println("entity" + entity);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        if(code==0){
        	return null;
        }else{
        	return String.valueOf(code);
        }
    }

    /*
     */

    /**
     * Method description get
     *
     *
     * @param url
     *
     * @return JSONObject 
     */
    public static JSONObject get(String url) {
        JSONObject content    = null;
        HttpClient httpClient = new DefaultHttpClient();

        try {
            HttpGet      httpget  = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpget);
            int          code     = response.getStatusLine().getStatusCode();

            if (code == 200) {
                String entity = EntityUtils.toString(response.getEntity(), "UTF-8");

                content = JSONObject.parseObject(entity);
            }

            httpget.abort();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }

        System.out.println("content:" + content);

        return content;
    }

    /**
     * Method description getString
     *
     *
     * @param url
     *
     * @return String 
     */
    public static String getString(String url) {
        String     content    = "";
        HttpClient httpClient = new DefaultHttpClient();

        try {
            HttpGet      httpget  = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpget);
            int          code     = response.getStatusLine().getStatusCode();

            if (code == 200) {
                content = EntityUtils.toString(response.getEntity(), "UTF-8");
            }

            httpget.abort();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }

        System.out.println("content:" + content);

        return content;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
