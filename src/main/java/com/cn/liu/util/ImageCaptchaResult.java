package com.cn.liu.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author huangrusheng
 * @version 1.0
 * @date 2021/5/20 10:27
 */
@Data
@AllArgsConstructor
public class ImageCaptchaResult {
    /**
     * 图片验证码文字内容
     */
    private String words;
    /**
     * 返回内容，例如正常的Base64或以Web的image标签识别的base64格式
     */
    private String returnContent;


}
