package org.basis.authuser.config;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/5/12 2:23 下午
 * @since JDK 1.8
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate配置类
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(  ) {
        return new RestTemplate( simpleClientHttpRequestFactory() );
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout( 10000 );//单位为ms
        factory.setConnectTimeout( 10000 );//单位为ms
        return factory;
    }
}

