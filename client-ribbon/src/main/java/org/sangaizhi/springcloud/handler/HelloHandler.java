package org.sangaizhi.springcloud.handler;

import org.sangaizhi.springcloud.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sangaizhi
 * @date 2017/9/20
 */
@RestController
public class HelloHandler {

    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name){
        return helloService.sayHi(name);
    }

    @RequestMapping(value = "/zuul")
    public String zuul(@RequestParam String name){
        return helloService.zuul(name);
    }
}
