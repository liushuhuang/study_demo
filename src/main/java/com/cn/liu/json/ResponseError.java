package com.cn.liu.json;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author liu
 */
@NoArgsConstructor
@AllArgsConstructor
public class ResponseError extends ResponseResult{

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    /**
     * 请求地址（发生异常时返回）
     */
    private String requestUrl;

    /**
     * 异常类（发生异常时返回）
     */
    private String exception;
}
