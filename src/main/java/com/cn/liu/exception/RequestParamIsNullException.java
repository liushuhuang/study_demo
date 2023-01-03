package com.cn.liu.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestParamIsNullException extends RuntimeException{

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;
}
