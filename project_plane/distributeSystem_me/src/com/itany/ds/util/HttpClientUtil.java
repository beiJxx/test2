package com.itany.ds.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.itany.ds.constant.ItanyConstants;


/**
 * <封装了一些采用HttpClient发送HTTP请求的方法>
 *
 * @author 崔译 cuiyi@itany.com
 * @version [V1.00, 14-8-26]
 * @see [相关类/方法]
 * @since V1.00
 */
public class HttpClientUtil {

    private static Logger logger = Logger.getLogger(HttpClientUtil.class);

    private static ThreadLocal<HttpClient> clients = new ThreadLocal<HttpClient>();

    private HttpClientUtil() {

    }

    private static HttpClient getClient()
    {
        HttpClient client = clients.get();
        if(client == null)
        {
            client = new DefaultHttpClient();
            clients.set(client);
        }
        return client;
    }

    /**
     * 发送HTTP_GET请求
     *
     * 该方法会自动关闭连接,释放资源
     * @param reqURL
     *            请求地址(含参数)
     * @param decodeCharset
     *            解码字符集,解析响应数据时用之,其为null时默认采用UTF-8解码
     * @return 远程主机响应正文
     */
    public static  String sendGetRequest(String reqURL, String decodeCharset) {
        // 响应内容
        String responseContent = null;
        //添加授权
        reqURL += "&" + ItanyConstants.APP_AUTH_SECRET + "=" + getAuth_srcret();
        HttpGet httpGet = new HttpGet(reqURL);
        try {
            // 执行GET请求
            HttpResponse response = getClient().execute(httpGet);
            // 获取响应实体
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity,
                        decodeCharset == null ? "UTF-8" : decodeCharset);
                EntityUtils.consume(entity);
            }
        } catch (Exception e) {
            logger.error("HttpClientUtil.sendGetRequest() error", e);
        }
        return responseContent;
    }

    /**
     * 发送HTTP_POST请求
     *
     *  该方法为<code>sendPostRequest(String,String,boolean,String,String)</code>
     *      的简化方法
     *  该方法在对请求数据的编码和响应数据的解码时,所采用的字符集均为UTF-8
     *  当<code>isEncoder=true</code>时,其会自动对<code>sendData</code>中的[中文][|][
     *      ]等特殊字符进行<code>URLEncoder.encode(string,"UTF-8")</code>
     * @param isEncoder
     *            用于指明请求数据是否需要UTF-8编码,true为需要
     */
    public  static String sendPostRequest(String reqURL, String sendData,
                                          boolean isEncoder) {
        return sendPostRequest(reqURL, sendData, isEncoder, null, null);
    }

    /**
     * 发送HTTP_POST请求
     *
     *  该方法会自动关闭连接,释放资源
     *  当<code>isEncoder=true</code>时,其会自动对<code>sendData</code>中的[中文][|][
     *      ]等特殊字符进行<code>URLEncoder.encode(string,encodeCharset)</code>
     * @param reqURL
     *            请求地址
     * @param sendData
     *            请求参数,若有多个参数则应拼接成param11=value11&m22=value22&m33=value33的形式后,
     *            传入该参数中
     * @param isEncoder
     *            请求数据是否需要encodeCharset编码,true为需要
     * @param encodeCharset
     *            编码字符集,编码请求数据时用之,其为null时默认采用UTF-8解码
     * @param decodeCharset
     *            解码字符集,解析响应数据时用之,其为null时默认采用UTF-8解码
     * @return 远程主机响应正文
     */
    public static   String sendPostRequest(String reqURL, String sendData,
                                           boolean isEncoder, String encodeCharset, String decodeCharset) {
        sendData += "&" + ItanyConstants.APP_AUTH_SECRET + "=" + getAuth_srcret();
        String responseContent = null;
        HttpPost httpPost = new HttpPost(reqURL);
        httpPost.setHeader(HTTP.CONTENT_TYPE,
                "application/x-www-form-urlencoded");
        try {
            if(sendData != null && !"".equals(sendData.trim()))
            {
                if (isEncoder) {
                    List<NameValuePair> formParams = new ArrayList<NameValuePair>();
                    for (String str : sendData.split("&")) {
                        formParams.add(new BasicNameValuePair(str.substring(0,
                                str.indexOf("=")),
                                str.substring(str.indexOf("=") + 1)));
                    }
                    httpPost.setEntity(new StringEntity(URLEncodedUtils.format(
                            formParams, encodeCharset == null ? "UTF-8"
                            : encodeCharset)));
                } else {
                    httpPost.setEntity(new StringEntity(sendData));
                }
            }

            HttpResponse response = getClient().execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity,
                        decodeCharset == null ? "UTF-8" : decodeCharset);
                EntityUtils.consume(entity);
            }
        } catch (Exception e) {
            logger.error("与[" + reqURL + "]通信过程中发生异常,堆栈信息如下", e);
        }
        return responseContent;
    }

    /**
     * 发送HTTP_POST请求
     *
     *  该方法会自动关闭连接,释放资源
     *  该方法会自动对<code>params</code>中的[中文][|][ ]等特殊字符进行
     *      <code>URLEncoder.encode(string,encodeCharset)</code>
     * @param reqURL
     *            请求地址
     * @param params
     *            请求参数
     * @param encodeCharset
     *            编码字符集,编码请求数据时用之,其为null时默认采用UTF-8解码
     * @param decodeCharset
     *            解码字符集,解析响应数据时用之,其为null时默认采用UTF-8解码
     * @return 远程主机响应正文
     */
    public static String sendPostRequest(String reqURL,
                                         Map<String, String> params, String encodeCharset,
                                         String decodeCharset) {
        params.put(ItanyConstants.APP_AUTH_SECRET,getAuth_srcret());
        String responseContent = null;

        HttpPost httpPost = new HttpPost(reqURL);
        List<NameValuePair> formParams = new ArrayList<NameValuePair>(); // 创建参数队列
        for (Map.Entry<String, String> entry : params.entrySet()) {
            formParams.add(new BasicNameValuePair(entry.getKey(), entry
                    .getValue()));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(formParams,
                    encodeCharset == null ? "UTF-8" : encodeCharset));

            HttpResponse response = getClient().execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity,
                        decodeCharset == null ? "UTF-8" : decodeCharset);
                EntityUtils.consume(entity);
            }
        } catch (Exception e) {
            logger.error("与[" + reqURL + "]通信过程中发生异常,堆栈信息如下", e);
        }
        return responseContent;
    }

    /**
     * 关闭HttpClient连接，在session销毁时调用
     */
    public static void closeClient()
    {
        clients.get().getConnectionManager().shutdown();
        clients.remove();
    }


    private static String  getAuth_srcret()
    {
        //初始化加密工具
        DESUtil desUtil = new DESUtil();
        desUtil.setKey(ItanyConstants.DES_KEY);
        //获取当前时间,转化为yyyyMMddHHmm格式
        Date curDate = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
        String curTime = sf.format(curDate);
        //生成密码
        String auth_secret = desUtil.getEncString(curTime);

        return  auth_secret;
    }
    

}