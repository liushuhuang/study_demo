package com.cn.liu.service.iml;

import com.alibaba.fastjson.JSONObject;
import com.cn.liu.entity.User;
import com.cn.liu.exception.BusinessException;
import com.cn.liu.service.TestService;
import com.cn.liu.util.mq.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

/**
 * @author liushuhuang
 * @date 2023/2/21
 */
@Service
public class TestServiceImpl implements TestService {
    @Resource
    private  Producer producer;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    RedisTemplate redisTemplate;

    @Override
    public void test1(Map<String,Object> map) {
        logger.info("开始测试");
        User user = JSONObject.toJavaObject((JSONObject)map.get("user"),User.class);
        user.setCardNo((String) map.get("goodsId"));
        if(redisTemplate.opsForSet().isMember("pre"+user.getCardNo(),user.getId())){
            throw new BusinessException("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        }
        Long aa = redisTemplate.opsForValue().decrement("ff");
        if (aa < 0){
            throw new BusinessException("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        }


        producer.sendMsg(user);
    }

    @Override
    @PostConstruct
    public void init() {
        redisTemplate.opsForValue().set("ff",30);
    }
}
