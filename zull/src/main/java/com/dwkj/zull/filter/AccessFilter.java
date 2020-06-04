package com.dwkj.zull.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 这个目前只是一个demo；等需要的时候从这个demo为模板，编写过滤器
 */
public class AccessFilter extends ZuulFilter {

    @Override
    public String filterType() {
        /**
         * pre：可以在请求被路由之前调用
         * route：在路由请求时候被调用
         * post：在route和error过滤器之后被调用
         * error：处理请求时发生错误时被调用
         */
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx=RequestContext.getCurrentContext();
        HttpServletRequest request=ctx.getRequest();
        String flag= request.getParameter("accessToken");
        boolean b=true;
        if("1".equals(flag)){
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("accessToken不能等于1");
            HttpServletResponse response=ctx.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            ctx.setResponse(response);
            return null;
        }
        return null;
    }
}
