package com.dwkj.web.controller;

import com.dwkj.base.bean.TestBean;
import com.dwkj.web.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {


    @Autowired
    private ITestService iTestService;

    @ResponseBody
    @RequestMapping("/test1")
    public String test1(){
        return iTestService.test1();
    }
    @ResponseBody
    @RequestMapping("/test2")
    public String test2(){
        return iTestService.test2("guoyansi",28);
    }
    @ResponseBody
    @RequestMapping("/test3")
    public String test3(){
        TestBean bean=new TestBean("郭延思");
        return iTestService.test3(bean);
    }
    @ResponseBody
    @RequestMapping("/test4")
    public String test4(String name){
        return iTestService.test4(name);
    }
}
