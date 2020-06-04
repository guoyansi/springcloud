package com.dwkj.service.controller;

import com.dwkj.base.fun.bean.test.TestBean;
import com.dwkj.base.fun.bean.test.TestForm;
import com.dwkj.service.dao.ITestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestControllerService {

    @Autowired
    private ITestDao iTestDao;

    /**
     * 接受一定要添加 @RequestParam("age")  age不能省略；springmvc中可以省略
     * @param age
     * @return
     */
    @RequestMapping("/getTestList")
    public List<TestBean> getTestList(@RequestParam("age") Integer age){
        System.out.println("age=" + age);
        return iTestDao.getTestList(age);
    }

    /**
     * 测试接受对象作为参数
     * @param form
     * @return
     */
    @RequestMapping("/testObjParam")
    public TestForm testObjParam(@RequestBody TestForm form){
        return form;
    }
}
