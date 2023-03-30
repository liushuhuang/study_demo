package com.cn.liu.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liushuhuang
 * @date 2023/3/10
 */
public class TokenUtil {
    public static String getRequestToken(HttpServletRequest request){
        return request.getHeader("token");
    }
}
