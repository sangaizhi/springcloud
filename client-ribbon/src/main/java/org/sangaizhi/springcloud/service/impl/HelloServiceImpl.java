package org.sangaizhi.springcloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.sangaizhi.springcloud.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author sangaizhi
 * @date 2017/9/20
 */
@Service
public class HelloServiceImpl implements HelloService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${spring.application.name}")
	private String applicationName;

	@Override
    // 使用断路器，指定的处理回退逻辑的方法为 hiError
	@HystrixCommand(fallbackMethod = "hiError")
	public String sayHi(String name) {
		// 应用负载均衡
		return restTemplate.getForObject("http://eureka-client/hi?name=" + name, String.class);
	}

	@Override
    // 使用断路器，指定的处理回退逻辑的方法为 hiError
	@HystrixCommand(fallbackMethod = "zuulError")
	public String zuul(String name) {
		// 应用负载均衡， 调用 eureka-client 服务的 zuul 接口
		return applicationName + ":"
			+ restTemplate.getForObject("http://eureka-client/zuul?name=" + name, String.class);
	}

	public String hiError(String name) {
		return "sorry," + name + ",error";
	}


	public String zuulError(String name){
		return "sorry," + name + ",error";
	}
}
