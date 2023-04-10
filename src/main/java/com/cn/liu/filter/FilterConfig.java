package com.cn.liu.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author liu
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> registFilter() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new LogCostFilter());
        registration.addUrlPatterns("/test/*");
        registration.setName("LogCostFilter");
        registration.setOrder(1);
        return registration;
    }

}
