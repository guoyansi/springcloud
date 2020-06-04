package com.dwkj.base.item.interceptor;

import com.alibaba.fastjson.JSON;
import com.dwkj.base.item.consts.ConstWeb;
import com.dwkj.base.item.session.IUserSession;
import com.dwkj.base.item.util.HttpResult;
import com.dwkj.base.item.util.MyTool;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class SessionFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        IUserSession user=(IUserSession) request.getSession().getAttribute(ConstWeb.userSession);
        if (isThrough(uri) || user != null) { //不需要过滤直接传给下一个过滤器
            filterChain.doFilter(servletRequest, servletResponse);
        } else { //需要过滤器
            if (!MyTool.isAjax(request)) {//普通请求
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().print("<script language='javascript'>");
                response.getWriter().print("window.location.href='" + request.getContextPath() + "/noSession';");
                response.getWriter().print("</script>");
            } else {//ajax请求
                response.setContentType("text/plain;charset=utf-8");
                HttpResult res = HttpResult.noSessionEx();
                response.getWriter().print(JSON.toJSONString(res));
            }
        }
    }

    @Override
    public void destroy() {

    }


    private boolean isThrough(String uri) {
        if(uri.indexOf("/static")>-1){
            return true;
        }else if (uri.indexOf("/loginPage") > -1) {
            return true;
        }else if(uri.indexOf("/noSession")>-0){
            return true;
        }if (uri.indexOf("/login") > -1) {
            return true;
        }else if(uri.indexOf("/test") > -1) {
            return true;
        }
        return false;
    }
}
