package com.dwkj.base.item.interceptor;

import com.dwkj.base.item.ex.MyException;
import com.dwkj.base.item.util.HttpResult;
import com.dwkj.base.item.util.MyTool;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ControllerAdvice
public class MyExceptionInterceptor {


    /**
     * web请求依然生效
     * @param request
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler({Exception.class})
    public Object exceptionHandler(HttpServletRequest request,Exception e){
        e.printStackTrace();
        if(MyTool.isAjax(request)){
            return HttpResult.errorEx();
        }else{
            ModelAndView view=new ModelAndView();
            view.addObject("msg","程序异常");
            view.setViewName("/public/exPage");
            return view;
        }

    }

    @ResponseBody
    @ExceptionHandler({MyException.class})
    public Object myExceptionHandler(HttpServletRequest request,MyException e){
        if(MyTool.isAjax(request)){
            return HttpResult.error(e.getMessage());
        }else{
            ModelAndView view=new ModelAndView();
            view.addObject("msg",e.getMessage());
            view.setViewName("/public/exPage");
            return view;
        }
    }


    /**
     * 每个页面都能访问到这里的属性
     * @param model
     */
    @ModelAttribute
    public void addAttribute(Model model, HttpSession session){

        /*Object objName=session.getAttribute("username");
        String username=null;
        if(objName!=null){
             username=objName.toString();
        }
        model.addAttribute("test",username);*/
    }




}
