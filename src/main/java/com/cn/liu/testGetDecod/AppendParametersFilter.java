package com.cn.liu.testGetDecod;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


// @WebFilter("/*")
public class AppendParametersFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Map<String, String[]> additionalParams = new HashMap<>();
        String a= "21";
        additionalParams.put("age", new String[] {a});
        additionalParams.put("name", new String[] {"liu"});
        EnhancedHttpRequest enhancedHttpRequest = new EnhancedHttpRequest((HttpServletRequest) request, additionalParams);

        // pass the request along the filter chain
        chain.doFilter(enhancedHttpRequest, response);
    }

    @Override
    public void destroy() {	}
    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }
}
