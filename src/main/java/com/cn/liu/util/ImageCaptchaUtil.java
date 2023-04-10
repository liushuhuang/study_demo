package com.cn.liu.util;

import cn.hutool.core.codec.Base64;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
/**
 * @author huangrusheng
 * @version 1.0
 *
 */
public final class ImageCaptchaUtil {

    private ImageCaptchaUtil(){}
    /**
     * 流方式输出
     * @param params  验证码配置
     * @param outputStream  输出流
     * @return 图片文字内容
     * @throws IOException 抛出io异常
     */
    public static String create(ImageCaptchaParams params, OutputStream outputStream) throws IOException{
        ConfigurableCaptchaService  captchaService = new ConfigurableCaptchaService();
        captchaService.setColorFactory(new SingleColorFactory(Color.DARK_GRAY));
        captchaService.setFilterFactory(new CurvesRippleFilterFactory(captchaService.getColorFactory()));
        captchaService.setHeight(params.getHeight());
        captchaService.setWidth(params.getWidth());
        RandomWordFactory wordFactory = new RandomWordFactory();
        wordFactory.setCharacters(params.getWords());
        wordFactory.setMaxLength(params.getMaxWordAmount());
        wordFactory.setMinLength(params.getMinWordAmount());
        captchaService.setWordFactory(wordFactory);
        RandomFontFactory fontFactory = new RandomFontFactory();
        fontFactory.setMaxSize(params.getMaxFontSize());
        fontFactory.setMinSize(params.getMinFontSize());
        captchaService.setFontFactory(fontFactory);
        return EncoderHelper.getChallangeAndWriteImage(captchaService,params.getFormat(),outputStream);
    }
    /**
     * 以base64方式返回
     * @param params 验证码配置
     * @return 图片文字内容+base64格式的图片
     * @throws IOException 抛出io异常
     */
    public static ImageCaptchaResult createBase64(ImageCaptchaParams params) throws IOException{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024 * 3);
        String words = create(params,outputStream);
        return new ImageCaptchaResult(words, Base64.encode(outputStream.toByteArray()));
    }
    /**
     * 以Web的img识别的base64返回
     * @param params  验证码配置
     * @return 图片文字内容+base64格式的img标签识别的图片
     * @throws IOException 抛出io异常
     */
    public static ImageCaptchaResult createBase64ForWeb(ImageCaptchaParams params) throws IOException{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024 * 3);
        String words = create(params,outputStream);
        return new ImageCaptchaResult(words, "data:image/" + params.getFormat().toLowerCase() + ";base64," + Base64.encode(outputStream.toByteArray()));
    }
}
