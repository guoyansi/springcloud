package com.dwkj.web.controller;

import com.alibaba.fastjson.JSON;
import com.dwkj.base.fun.bean.test.TestBean;
import com.dwkj.base.fun.bean.test.TestForm;
import com.dwkj.web.api.IWebTestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class WebTestController {

    @Autowired
    private IWebTestApi iWebTestApi;


    //配置中心中的配置
    @Value("${word}")
    private String word;

    //配置中心中的配置
    @Value("${gys}")
    private String gys;

    @RequestMapping("/getTestList")
    public ModelAndView getTestList(Integer age, HttpServletRequest request, HttpServletResponse response){



        Cookie[] cs=request.getCookies();
        System.out.println("==============");
        for (Cookie c : cs) {
            System.out.println(c.getName());
        }
        System.out.println("============");
        Cookie ck=new Cookie("gys","gys=====");
        //response.addCookie(ck);
        List<TestBean> list=iWebTestApi.getTestList(age);
        ModelAndView view =new ModelAndView();
        view.addObject("list",list);
        view.addObject("listJson",  JSON.toJSONString(list));
        System.out.println(JSON.toJSONString(list));
        view.setViewName("/test");



        return view;
    }


    /**
     * 测试传递对象
     * @param form
     * @return
     */
    @ResponseBody
    @RequestMapping("/testObjParam")
    public TestForm testObjParam(TestForm form){
        TestForm res=iWebTestApi.testObject(form);

        return res;
    }


    @RequestMapping("/cdx")
    public String redict(){
        return "redirect:/cdx1";
    }

    @RequestMapping("/cdx1")
    public String redict1(){
        return "/test1";
    }
}
