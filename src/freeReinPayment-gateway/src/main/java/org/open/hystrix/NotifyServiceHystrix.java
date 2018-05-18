package org.open.hystrix;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

public class NotifyServiceHystrix implements ZuulFallbackProvider{
	public String getRoute() {

        return "SERVICE-NOTIFY";
    }

    public ClientHttpResponse fallbackResponse() {

        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
/*            	HNAResult<Object> hnaResult = new HNAResult<>();
            	hnaResult.setStateCode("-9999");
            	hnaResult.setDetailInfo("airplane-fallback");
            	ByteArrayOutputStream byt=new ByteArrayOutputStream();
                ObjectOutputStream obj=new ObjectOutputStream(byt);
                obj.writeObject(hnaResult);
                byte[] array = byt.toByteArray();*/
                return new ByteArrayInputStream("{\"error\":\"SERVICE-NOTIFY_fallback\"}".getBytes());
/*                return new ByteArrayInputStream(array);*/
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                //headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
