package au.com.nab.icommerce.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@ComponentScan(basePackages = {"au.com.nab.icommerce.base.*", "au.com.nab.icommerce.order.*"})
public class NabIcommerceOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NabIcommerceOrderServiceApplication.class, args);
	}

}
