package org.sangaizhi.springcloud.facade;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author sangaizhi
 * @date 2017/9/20
 */
@Component
public class HelloServiceHystrix implements HelloService {


    @Override
    public String sayHiFromClient(@RequestParam(value = "name") String name) {
        return "sorry," + name + ",error";
    }

    @Override
    public String zuul(@RequestParam(value = "name") String name) {
        return "sorry," + name + ",error";
    }
}
