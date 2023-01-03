package com.cn.liu.Json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseError extends ResponseResult{

    /**
     * 请求地址（发生异常时返回）
     */
    private String requestUrl;

    /**
     * 异常类（发生异常时返回）
     */
    private String exception;
}
