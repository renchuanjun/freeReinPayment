

package org.open.feigns;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

/**
 * MythRestTemplateInterceptor.
 * @author xiaoyu
 */
@Configuration
public class MyRestTemplateInterceptor implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate template) {
//        template.header("MYTH_TRANSACTION_CONTEXT", "11");
    }
}
