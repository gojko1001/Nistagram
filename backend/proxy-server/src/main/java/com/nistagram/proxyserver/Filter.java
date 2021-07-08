//package com.nistagram.proxyserver;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Set;
//
//@Component
//public class Filter extends ZuulFilter {
//    @Override
//    public String filterType() {
//        return "pre";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 10000;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return  true;
//    }
//
//    @Override
//    public Object run() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//
//        Set<String> headers = (Set<String>) ctx.get("ignoredHeaders");
//        headers.remove("authorization");
//
//
//        return null;
//    }
//}
