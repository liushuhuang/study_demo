package com.cn.liu.config;

import org.springframework.core.MethodParameter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class CustomGetRequestResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 判断该方法是否需要使用自定义解析器
        return parameter.hasParameterAnnotation(GetRequestParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        // 获取注解信息，即参数名
        GetRequestParam annotation = parameter.getParameterAnnotation(GetRequestParam.class);
        String paramName = annotation.value();

        // 从 request 中获取参数值
        String[] paramValues = webRequest.getParameterValues(paramName);

        // 如果没有对应参数，则返回 null
        if (paramValues == null || paramValues.length == 0) {
            return null;
        }

        // 如果有多个对应参数，则返回数组
        if (paramValues.length > 1) {
            return paramValues;
        }

        // 否则返回第一个参数值
        return paramValues[0];
    }
}
