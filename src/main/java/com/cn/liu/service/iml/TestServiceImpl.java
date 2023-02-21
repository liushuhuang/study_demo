package com.cn.liu.service.iml;

import com.cn.liu.service.TestService;
import com.cn.liu.util.MQ.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liushuhuang
 * @date 2023/2/21
 */
@Service
public class TestServiceImpl implements TestService {
    @Resource
    private  Producer producer;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void test1() {
        logger.info("开始测试");
        producer.sendMsg("测试成功！");
    }
}
