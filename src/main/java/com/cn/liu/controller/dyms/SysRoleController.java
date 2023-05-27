package com.cn.liu.controller.dyms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/role")
public class SysRoleController {

    @PostMapping("/list")
    public void list(@RequestBody Map<String,Object> map) {
        JSONObject user1 = JSONObject.parseObject(JSON.toJSONString(map.get("role")));
        SysRole role = JSONObject.toJavaObject(user1, SysRole.class);
        System.out.println(role.toString());
    }

    /**
     * 根据角色编号获取详细信息
     */
    @PostMapping(value = "/queryById")
    public void getInfo(@RequestBody Long roleId) {
        System.out.println(roleId);
    }

    /**
     * 删除角色
     */
    @PostMapping ("/deleteById")
    public void remove(@RequestBody Long[] roleIds) {
        for (Long roleId : roleIds) {
            System.out.println(roleId);
        }
    }

    /**
     * 查询已分配用户角色列表
     */
    @PostMapping("/authUser/allocatedList")
    public void allocatedList(@RequestBody Map<String,Object> map) {
        JSONObject user1 = JSONObject.parseObject(JSON.toJSONString(map.get("user")));
        SysUser role = JSONObject.toJavaObject(user1, SysUser.class);
        System.out.println(role.toString());
    }

    /**
     * 查询未分配用户角色列表
     */
    @PostMapping("/authUser/unallocatedList")
    public void unallocatedList(@RequestBody Map<String,Object> map) {
        JSONObject user1 = JSONObject.parseObject(JSON.toJSONString(map.get("user")));
        SysUser role = JSONObject.toJavaObject(user1, SysUser.class);
        System.out.println(role.toString());
    }

    /**
     * 批量取消授权用户
     */
    @PostMapping("/authUser/cancelAll")
    public void cancelAuthUserAll(@RequestBody Map<String,Object> map) {
        Long roleId = Long.valueOf(map.get("roleId").toString());
        System.out.println(roleId);
        List<Long> list = JSON.parseArray(JSON.toJSONString(map.get("userIds")), Long.class);
        Long[] userIds = list.toArray(new Long[0]);
        for (Long userId : userIds) {
            System.out.println("userId"+userId);
        }
    }
    //
    /**
     * 批量选择用户授权
     */
    @PostMapping("/authUser/selectAll")
    public void selectAuthUserAll(@RequestBody Map<String,Object> map) {
        Long roleId = Long.valueOf(map.get("roleId").toString());
        System.out.println(roleId);
        List<Long> list = JSON.parseArray(JSON.toJSONString(map.get("userIds")), Long.class);
        Long[] userIds = list.toArray(new Long[0]);
        for (Long userId : userIds) {
            System.out.println("userId"+userId);
        }
    }
    //
    /**
     * 获取对应角色部门树列表
     */
    @PostMapping(value = "/deptTree")
    public void deptTree(@RequestBody Long roleId) {
        System.out.println(roleId);
    }
}
