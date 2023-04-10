package com.cn.liu.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author liu
 */
@NoArgsConstructor
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BusinessException(String message){
        this.code = "BUSINESS_ERROR";
        this.message = message;
    }
}
