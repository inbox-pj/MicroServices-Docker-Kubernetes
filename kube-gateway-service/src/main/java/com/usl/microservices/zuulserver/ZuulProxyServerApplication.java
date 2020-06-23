package com.usl.microservices.zuulserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@EnableZuulProxy
public class ZuulProxyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulProxyServerApplication.class, args);
	}

}
