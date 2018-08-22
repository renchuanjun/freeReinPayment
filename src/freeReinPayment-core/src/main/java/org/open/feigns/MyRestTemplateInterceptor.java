

package org.open.feigns;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * MythRestTemplateInterceptor.
 * @author xiaoyu
 */
@Configuration
public class MyRestTemplateInterceptor implements RequestInterceptor {



    @Override
    public void apply(RequestTemplate template) {
        System.out.println("2222");
    }
}
