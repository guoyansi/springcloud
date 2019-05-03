package com.dwkj.web.service;

import com.dwkj.base.bean.TestBean;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("test-service")
public interface ITestService{



    @RequestMapping("/test/test1")
    String test1();

    /**
     * @RequestParam 必须添加否则启动报错：
     * Method has too many Body parameters:
     * @param name
     * @param age
     * @return
     */
    @RequestMapping("/test/test2")
    String test2(@RequestParam("name") String name,@RequestParam("age") Integer age);

    /**
     * @RequestBody 必须添加否则启动报错：
     * @param bean
     * @return
     */
    @RequestMapping("/test/test3")
    String test3(@RequestBody TestBean bean);

    @RequestMapping("/test/test4")
    String test4(@RequestParam("name") String name);
}
