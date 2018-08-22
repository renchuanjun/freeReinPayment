package org.open.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import org.open.config.BaseConfigProperties;
import org.open.utils.AppUtils;
import org.open.utils.Base64Utils;
import org.open.utils.RsaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.Calendar;
import java.util.Collection;
import java.util.Map;

/**
 * 远程调用的拦截器
 * Created by lenovo on 2017/5/2.
 */
public class RemoteRequestInterceptor implements RequestInterceptor {

    private BaseConfigProperties baseConfigProperties;

    private ResourceLoader resourceLoader;

    public BaseConfigProperties getBaseConfigProperties() {
        return baseConfigProperties;
    }

    public void setBaseConfigProperties(BaseConfigProperties baseConfigProperties) {
        this.baseConfigProperties = baseConfigProperties;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {

        System.out.println(baseConfigProperties.isRsa());
        if (baseConfigProperties.isRsa()){
            String priKeyXml = "";//私钥内容
            String paramData = "";//请求参数内容
            String sign = "";//参数签名码
            String module = "";//私钥文件所对应的服务名称
            //时间戳值
            String timestamp = String.valueOf(Calendar.getInstance().getTimeInMillis());
            String method = requestTemplate.method();
            //通过header获取签名私钥文件路径
            Map<String, Collection<String>> headers= requestTemplate.headers();
            if (null != headers && !headers.isEmpty()){
                //获取私钥路径
                Collection<String> collection = headers.get("privateFile");
                Object[] arr = collection.toArray();
                module= arr[0].toString();
            }

            //获取请求参数的内容
            if ("GET".equals(method)){
                paramData = requestTemplate.url();
            }
            else if ("POST".equals(method)){
                byte[] by = requestTemplate.body();
                paramData = new String(by);
            }
            System.out.println(paramData+"_____"+timestamp);

            try {
                priKeyXml = AppUtils.getFileContent(resourceLoader,"classpath:private_"+module+".xml");
                //加载私钥
                PrivateKey priKey = RsaHelper.decodePrivateKeyFromXml(priKeyXml);

                byte[] dataByteArray = (paramData+timestamp).getBytes("utf-8");

                byte[] signDataByteArray = RsaHelper.signData(dataByteArray,priKey);

                sign = Base64Utils.encode(signDataByteArray);
                sign = sign.replaceAll("\r\n", "").replaceAll("\r","").replaceAll("\n", "");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            requestTemplate.header("sign",sign);
            requestTemplate.header("module",module);
            requestTemplate.header("timestamp",timestamp);
        }

    }
}
