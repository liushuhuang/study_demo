// package com.cn.liu.filter;
//
// import com.cn.liu.testGetDecod.AppendParametersFilter;
// import org.springframework.boot.web.servlet.FilterRegistrationBean;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// import javax.servlet.Filter;
//
// /**
//  * @author liu
//  */
// @Configuration
// public class FilterConfig {
//
//     @Bean
//     public FilterRegistrationBean<Filter> registFilter() {
//         FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
//         registration.setFilter(new AppendParametersFilter());
//         registration.addUrlPatterns("/*");
//         registration.setName("AppendParametersFilter");
//         registration.setOrder(1);
//         return registration;
//     }
//
// }
