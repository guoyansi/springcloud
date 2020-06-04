package com.dwkj.service.controller;

import com.dwkj.base.fun.bean.test.TestBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController{

    @RequestMapping("/test1")
    public String test1() {
        return "test afdfds success";
    }

    /**
     * 这个地方一天要把@RequestParam添加上，否则没有返回值
     * @param name
     * @param age
     * @return
     */
    @RequestMapping("/test2")
    public String test2(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        return "name:"+name+";age:"+age;
    }

    /**
     * 这个地方一天要把@RequestBody，否则没有返回值
     * @param testBean
     * @return
     */
    @RequestMapping("/test3")
    public String test3(@RequestBody TestBean testBean) {
        return "实体类：name:"+testBean.getName();
    }
    /**
     * 这个地方一天要把@RequestBody，否则没有返回值
     * @return
     */
    @RequestMapping("/test4")
    public String test4(String name) {
        return "name:"+name;
    }
    /*@RequestMapping("/test1")
    public String test1(){
        return "test success";
    }*/
}
