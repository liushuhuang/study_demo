package com.cn.liu.Interceptor;

import com.alibaba.fastjson.JSON;
import com.cn.liu.Json.ResponseResult;
import com.cn.liu.entity.User;
import com.cn.liu.mapper.UserMapper;
import com.cn.liu.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Resource
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token = TokenUtil.getRequestToken(request);
        //如果token为空
        if (StringUtils.isBlank(token)) {
            setReturn(response,400,"用户未登录，请先登录");
            return false;
        }
        //1. 根据token，查询用户信息
        User userEntity = userMapper.selectUserById(1);
        //2. 若用户不存在,
        if (userEntity == null) {
            setReturn(response,400,"用户不存在");
            return false;
        }
        //3. token失效
        //if (userEntity.getExpireTime().isBefore(LocalDateTime.now())) {
        //    setReturn(response,400,"用户登录凭证已失效，请重新登录");
        //    return false;
        //}

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
    //返回错误信息
    private static void setReturn(HttpServletResponse response, int status, String msg) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin","*");
        //UTF-8编码
        httpResponse.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        ResponseResult build = ResponseResult.fail("认证失败");
        String json = JSON.toJSONString(build);
        httpResponse.getWriter().print(json);
    }

}
