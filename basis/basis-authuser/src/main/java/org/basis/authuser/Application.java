package org.basis.authuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

import com.spring4all.swagger.EnableSwagger2Doc;

/**
 * 授权服务.<br/>
 * ClassName: Application <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON -- Optional. <br/>
 * date: 2020年2月24日 下午4:05:16 <br/>
 *
 * @author liuheqin
 * @version
 * @since JDK 1.8
 */
@SpringBootApplication(scanBasePackages = "org.basis")
@EnableDiscoveryClient
@EnableSwagger2Doc
@EnableFeignClients(basePackages = { "org.basis.common.sdk", "org.basis.authuser.client" })
@EnableAsync
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
