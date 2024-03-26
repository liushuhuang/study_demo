package com.cn.liu.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult {

    /**
     * 响应编码
     */
    protected String code;

    /**
     * 响应信息
     */
    protected String message;

    /**
     * 响应数据
     */
    protected Object data;

    /**
     * 请求成功
     */
    public static ResponseResult success(){
        return ResponseResult.builder().code("SUCCESS").message("请求成功").build();
    }

    /**
     * 请求成功
     */
    public static ResponseResult success(String message){
        return ResponseResult.builder().code("SUCCESS").message(message).build();
    }

    /**
     * 请求成功
     */
    public static ResponseResult success(String message, Object data){
        return ResponseResult.builder().code("SUCCESS").message(message).data(data).build();
    }

    /**
     * 请求成功
     */
    public static ResponseResult success(Object data){
        return ResponseResult.builder().code("SUCCESS").data(data).build();
    }

    /**
     * 请求失败
     */
    public static ResponseResult fail(){
        return ResponseResult.builder().code("FAIL").message("请求失败").build();
    }


    /**
     * 请求失败
     */
    public static ResponseResult fail(String message){
        return ResponseResult.builder().code("FAIL").message(message).build();
    }

    /**
     * 请求失败
     */
    public static ResponseResult fail(String message, Object data){
        return ResponseResult.builder().code("FAIL").message(message).data(data).build();
    }
}
