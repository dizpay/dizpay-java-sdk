package com.dizpay.api.util;

import okhttp3.*;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {
  public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");

  public static String httpGet(String url) throws IOException {
    OkHttpClient httpClient = new OkHttpClient();
    Request request = new Request.Builder()
        .url(url)
        .build();
    Response response = httpClient.newCall(request).execute();
    return response.body().string(); // 返回的是string 类型，json的mapper可以直接处理
  }

  public static String httpPostForStr(String url, String json) throws IOException {
    OkHttpClient httpClient = new OkHttpClient();
    RequestBody requestBody = RequestBody.create(JSON, json);
    Request request = new Request.Builder()
        .url(url)
        .post(requestBody)
        .build();
    Response response = httpClient.newCall(request).execute();
    return response.body().string();
  }

  /**
   * return Response
   * @param url
   * @param json
   * @return
   * @throws IOException
   */
  public static Response httpPost(String url, String json) throws IOException {
    OkHttpClient httpClient = new OkHttpClient();
    RequestBody requestBody = RequestBody.create(JSON, json);
    Request request = new Request.Builder()
            .url(url)
            .post(requestBody)
            .build();
    Response response = httpClient.newCall(request).execute();
    return response;
  }


  // 发起HTTP POST请求
  public static String sendPost(String url, Map<String, Object> map, String encoding){
    String body = "";
    CloseableHttpResponse response = null;
    try {
      CloseableHttpClient client = HttpClients.createDefault();
      HttpPost httpPost = new HttpPost(url);
      RequestConfig requestConfig = RequestConfig.custom()
          .setConnectTimeout(3000)
          .setConnectionRequestTimeout(3000)
          .setSocketTimeout(3000).build();
      httpPost.setConfig(requestConfig);

      List<NameValuePair> nvps = new ArrayList<NameValuePair>();
      //nvps.add(new BasicNameValuePair("tonce", Instant.now().toString()));
      if (map != null) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
          nvps.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
        }
      }
      httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));
      httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
      httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

      response = client.execute(httpPost);
      HttpEntity entity = response.getEntity();
      if (entity != null) {
        body = EntityUtils.toString(entity, encoding);
      }
      EntityUtils.consume(entity);
    } catch (IOException e) {

    } finally {
      if (response != null) {
        try {
          response.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return body;
  }


}
