package com.dwkj.base.router;

import com.dwkj.base.bean.TestBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/router")
public interface ITestRouter {


    @RequestMapping("/test1")
    String test1();

    @RequestMapping("/test2")
    String test2(@RequestParam("name") String name,@RequestParam("age") Integer age);

    @RequestMapping("/test3")
    String test3(@RequestBody TestBean testBean);
}
