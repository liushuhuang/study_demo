package com.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Arrays;

/**
 * @author liushuhuang
 * @date 2023/9/26
 */
public class userListener extends AnalysisEventListener<User> {

    @Override
    public void invoke(User user, AnalysisContext analysisContext) {
        System.out.println(Arrays.toString(user.file));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
