package com.cn.liu.service.iml;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.liu.json.ResponseResult;
import com.cn.liu.entity.TreeNode;
import com.cn.liu.entity.User;
import com.cn.liu.exception.BusinessException;
import com.cn.liu.service.TestService;
import com.cn.liu.util.mq.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
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
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public void test1(Map<String,Object> map) {
        logger.info("开始测试");
        User user = JSONObject.toJavaObject((JSONObject)map.get("user"),User.class);
        user.setCardNo((String) map.get("goodsId"));
        if(redisTemplate.opsForSet().isMember("pre"+user.getCardNo(),user.getId())){
            throw new BusinessException("你已经领取过，无法重复领取");
        }
        Long aa = redisTemplate.opsForValue().decrement("ff");
        if (aa < 0){
            throw new BusinessException("对不起，全部领完了");
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
        treeNodeList.add(new TreeNode(3, 1, "节点C,父节点是A"));
        treeNodeList.add(new TreeNode(4, 2, "节点D,父节点是B"));
        treeNodeList.add(new TreeNode(5, 2, "节点E,父节点是B"));

        treeNodeList.add(new TreeNode(6, 3, "节点F,父节点是C"));
        treeNodeList.add(new TreeNode(7, 4, "节点G,父节点是D"));
        treeNodeList.add(new TreeNode(8, 5, "节点H,父节点是E"));
        treeNodeList.add(new TreeNode(9, 6, "节点I,父节点是F"));
        treeNodeList.add(new TreeNode(10, 7, "节点J,父节点是G"));
        treeNodeList.add(new TreeNode(11, 8, "节点K,父节点是H"));
        return ResponseResult.success("成功",buileTree(treeNodeList));
    }
    private List<TreeNode> buileTree(List<TreeNode> treeNodeList){
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        for (TreeNode treeNode : treeNodeList) {
            if(treeNode.getParentId() == 0){
                findChildern(treeNode,treeNodeList);
                treeNodes.add(treeNode);
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
    @Override
    public void test3(Map<String,Object> map){
        logger.info("开始测试");
        User user = JSONObject.toJavaObject(JSONObject.parseObject(JSON.toJSONString(map.get("user"))),User.class);
        user.setCardNo((String) map.get("goodsId"));
        producer.sendTopicMsg(user);
    }

    @Override
    public void test4(Map<String,Object> map){
        logger.info("开始测试");
        User user = JSONObject.toJavaObject(JSONObject.parseObject(JSON.toJSONString(map.get("user"))),User.class);
        user.setCardNo((String) map.get("goodsId"));
        producer.sendMsg(user);
    }
}
