package com.cn.liu.service.iml;

import com.alibaba.fastjson.JSONObject;
import com.cn.liu.Json.ResponseResult;
import com.cn.liu.entity.TreeNode;
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
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    public void init() {
        redisTemplate.opsForValue().set("ff",30);
    }

    @Override
    public ResponseResult test2(){
        List<TreeNode> treeNodeList = new ArrayList<>();
        treeNodeList.add(new TreeNode(1, 0, "顶级节点A"));
        treeNodeList.add(new TreeNode(2, 0, "顶级节点B"));
        treeNodeList.add(new TreeNode(3, 1, "父节点是A"));
        treeNodeList.add(new TreeNode(4, 2, "父节点是B"));
        treeNodeList.add(new TreeNode(5, 2, "父节点是B"));
        return ResponseResult.success("成功",treeNodeList);
    }
    private List<TreeNode> buileTree(List<TreeNode> treeNodeList){
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        for (TreeNode treeNode : treeNodeList) {
            if(treeNode.getId() == 0){
                findChildern(treeNode,treeNodeList);
            }
        }
        return treeNodes;
    }

    private void findChildern(TreeNode treeNode,List<TreeNode> treeNodeList){
        List<TreeNode> childern = new ArrayList<TreeNode>();
        for (TreeNode node : treeNodeList) {
            if(node.getParentId().equals(treeNode.getId())){
                findChildern(node,treeNodeList);
                childern.add(node);
            }
        }
        treeNode.setChildren(childern);
    }


}
