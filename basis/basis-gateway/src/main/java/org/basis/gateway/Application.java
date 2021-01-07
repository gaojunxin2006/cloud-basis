package org.basis.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * 服务网关.<br/>
 * ClassName: Application <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON -- Optional. <br/>
 * date: 2020年2月24日 下午4:04:32 <br/>
 *
 * @author liuheqin
 * @version
 * @since JDK 1.8
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class Application {

	public static void main(String[] args) {
		System.out.println("20201231项目启动了。。。。。。。。。。。。。。");
		SpringApplication.run(Application.class, args);
	}
}
