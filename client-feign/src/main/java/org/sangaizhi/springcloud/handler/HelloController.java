package org.sangaizhi.springcloud.handler;

import org.sangaizhi.springcloud.facade.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sangaizhi
 * @date 2017/9/20
 */
@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @Value("${spring.application.name}")
    private String applicationName;

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String sayHi(@RequestParam String name){
        return helloService.sayHiFromClient(name);
    }


    @RequestMapping(value = "/zuul",method = RequestMethod.GET)
    public String zuul(@RequestParam String name){
        return applicationName + ":" +  helloService.zuul(name);
    }
}
