package org.basis.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.spring4all.swagger.EnableSwagger2Doc;

/**
 * 配置服务.<br/>
 * ClassName: Application <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON -- Optional. <br/>
 * date: 2020年2月25日 下午1:44:38 <br/>
 *
 * @author liuheqin
 * @version
 * @since JDK 1.8
 */
@SpringBootApplication(scanBasePackages = "org.basis")
@EnableDiscoveryClient
@EnableSwagger2Doc
@EnableScheduling
@EnableFeignClients(basePackages = "org.basis.common.sdk")
@EnableAsync
public class Application {
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Application.class, args);
	}
}
