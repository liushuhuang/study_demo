package com.cn.liu.service.iml;

import com.cn.liu.entity.User;
import com.cn.liu.service.TestService;
import com.cn.liu.util.MQ.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.ObjectMessage;

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
    public void test1(String str) {
        logger.info("开始测试");
        User user = new User();
        user.setAge(12);
        user.setId(1);
        user.setName("liu");
        user.setSex("man");
        user.setCardNo("1234567890");
        producer.sendMsg(user);
    }
}
