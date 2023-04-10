package com.cn.liu.util;

import lombok.Data;

/**
 * @author huangrusheng
 * @version 1.0
 *
 */
@Data
public class ImageCaptchaParams {
    /**
     * 默认图片验证码的文字内容
     */
    public static final String DEFAULT_WORDS = "123456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
    public static final String DEFAULT_FORMAT = "png";
    /**
     * 图片验证码宽度
     */
    private int width;
    /**
     * 图片验证码高度
     */
    private int height;
    /**
     * 最多生成几个文字
     */
    private int maxWordAmount;
    /**
     * 最小生成几个文字
     */
    private int minWordAmount;
    /**
     * 字体最大尺寸
     */
    private int maxFontSize;
    /**
     * 文字最小尺寸
     */
    private int minFontSize;
    /**
     * 生成图片验证码内容的字体
     */
    private String words;
    /**
     * 图片类型
     */
    private String format;

    public ImageCaptchaParams(){
        this.width = 200;
        this.height = 60;
        this.maxWordAmount = 5;
        this.minWordAmount = 4;
        this.minFontSize = 40;
        this.maxFontSize = 50;
        this.words = DEFAULT_WORDS;
        this.format = DEFAULT_FORMAT;
     }
  }
