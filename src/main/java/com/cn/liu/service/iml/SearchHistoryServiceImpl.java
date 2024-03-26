package com.cn.liu.service.iml;

import com.cn.liu.service.SearchHistoryService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author liushuhuang
 * @date 2024/3/26
 */
@Service
public class SearchHistoryServiceImpl implements SearchHistoryService {
    @Resource
    RedisTemplate redisTemplate;
    public static final String key = "liu";

    public static final long start = 1;

    public static final long size = 10;

    @Override
    public void search(String s) {
        addKey(s);
    }


    void addKey(String s){
        Double score = redisTemplate.opsForZSet().score(key, s);
        if (Objects.nonNull(score)){
            redisTemplate.opsForZSet().remove(key,s);
        }
        redisTemplate.opsForZSet().add(key,s,System.currentTimeMillis());
        Long size1 = redisTemplate.opsForZSet().size(key);
        if (size1 > 10){
            redisTemplate.opsForZSet().removeRange(key,start-1,size1-size-1);
        }
    }

    @Override
    public List<String> history() {
        List<String> res = new ArrayList<>();
        Set<ZSetOperations.TypedTuple> set = redisTemplate.opsForZSet().reverseRangeWithScores(key, start - 1, size - 1);
        Iterator<ZSetOperations.TypedTuple> iterator = set.iterator();
        BigDecimal bigDecimal = null;
        while (iterator.hasNext()){
            ZSetOperations.TypedTuple next = iterator.next();
            bigDecimal = BigDecimal.valueOf(next.getScore());
            System.out.println("==》ID： "+next.getValue()+" 时间： "+bigDecimal.toPlainString());
            if (Objects.nonNull(next.getValue())){
                res.add(next.getValue().toString());
            }
        }
        return res;
    }
}
