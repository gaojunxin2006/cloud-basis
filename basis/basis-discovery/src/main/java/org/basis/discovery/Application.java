package org.basis.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import com.spring4all.swagger.EnableSwagger2Doc;

/**
 * 辅助注册与发现.<br/>
 * ClassName: Application <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON -- Optional. <br/>
 * date: 2020年2月24日 下午4:44:22 <br/>
 *
 * @author liuheqin
 * @version
 * @since JDK 1.8
 */
@EnableEurekaServer 
@SpringBootApplication
@EnableSwagger2Doc
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
