package org.sangaizhi.springcloud.facade;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 定义 feign 接口
 * created by sangaizhi on 2017/9/20.
 */

/**
 * 定义 feign 接口，通过 value属性指定调用那个服务
 */
@FeignClient(value = "eureka-client", fallback = HelloServiceHystrix.class)
public interface HelloService {

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String sayHiFromClient(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/zuul", method = RequestMethod.GET)
    String zuul(@RequestParam(value = "name") String name);

}
