package com.dwkj.web.api;

import com.dwkj.base.fun.bean.test.TestBean;
import com.dwkj.base.fun.bean.test.TestForm;
import com.dwkj.web.consts.WebCont;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = WebCont.service_name)
public interface IWebTestApi {

    /**
     * 此处的@RequestParam("age")都要保留，否则service层根据这里的age接受参数
     * @param age
     * @return
     */
    @RequestMapping("/getTestList")
    List<TestBean> getTestList(@RequestParam("age") Integer age);


    /**
     * 测试传递对象作为参数
     * @param form
     * @return
     */
    @RequestMapping("/testObjParam")
    TestForm testObject(@RequestBody TestForm form);

}
