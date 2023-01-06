package com.cn.liu.controller;


import com.alibaba.fastjson.JSON;
import com.cn.liu.Json.ResponseResult;
import com.cn.liu.entity.Login;
import com.cn.liu.entity.User;
import com.cn.liu.exception.BusinessException;
import com.cn.liu.mapper.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author liu
 */
@Controller

public class LoginController {
    public final UserMapper userMapper;

    public LoginController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @PostMapping("/aa")
    @ResponseBody
    public void login(@RequestBody Map<Object,Object> dateMap){
        System.out.println(dateMap);
    }

    @PostMapping("/b")
    @ResponseBody
    public void b(String username , String password){
        System.out.println(username + "---------------" + password);
    }


    @PostMapping("/c")
    @ResponseBody
    public void c(@RequestParam("cc") String username){
        System.out.println(username + "---------------" + username);
    }

    @PostMapping("/d")
    @ResponseBody
    public String d(@RequestBody Map<Object,Object> dateMap) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(dateMap);
    }

    @PostMapping("/e")
    @ResponseBody
    public String e(@RequestBody Map<Object,Object> dateMap) {
        return JSON.toJSONString(dateMap);
    }

    @PostMapping("/f")
    @ResponseBody
    public String f(@RequestBody User u1) {
        System.out.println(u1.toString());
        userMapper.resgity(u1);
        return JSON.toJSONString(u1);
    }

    @PostMapping("/query/{id}")
    @ResponseBody
    public ResponseResult queryById(@PathVariable(name = "id") int id) {
        User user = userMapper.selectUserById(id);
        if(user == null){
            throw new RuntimeException("用户不存在");
        }
        return  ResponseResult.success("查询成功", userMapper.selectUserById(id));

    }
    @PostMapping("/newuser")
    @ResponseBody
    public ResponseResult newuser(@RequestBody User user) {
        System.out.println(user);
        int res = userMapper.resgity(user);

        return ResponseResult.success("新增用户成功", res);
    }

    @RequestMapping("/exception")
    public ResponseResult ExceptionTest(){
        throw new RuntimeException("异常测试");

    }

    @RequestMapping("/list")
    @ResponseBody
    public ResponseResult ListTest(){
        List<User> userList = userMapper.queryForList();
        return ResponseResult.success("查询成功", userList);
    }

    @RequestMapping("/login")
    @ResponseBody
    public ResponseResult login(@RequestBody Login login){
        User user = userMapper.login(login);
        if(user == null){
            throw new BusinessException("用户名或密码错误");
        }
        return ResponseResult.success("登录成功",user);
    }

    @RequestMapping("/resgity")
    @ResponseBody
    public ResponseResult resgity(@RequestBody User user){
        int res = userMapper.resgity(user);
        if(!(res>0)){
            throw new BusinessException("注册失败");
        }
        return ResponseResult.success("注册成功",user);
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public ResponseResult delete(@PathVariable(name = "id") int id){
        int res = userMapper.deleteUser(id);
        if(!(res>0)){
            throw new BusinessException("删除失败");
        }
        return ResponseResult.success("删除成功");
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResponseResult update(@RequestBody User user){
        int res = userMapper.update(user);
        if(!(res>0)){
            throw new BusinessException("更新失败");
        }
        return ResponseResult.success("更新成功",user);
    }

    @RequestMapping("/updatepwd")
    @ResponseBody
    public ResponseResult updatepwd(@RequestBody Map<String,String> newpwd){
        int res = userMapper.updatepwd(newpwd);
        if(!(res>0)){
            throw new BusinessException("更新失败");
        }
        return ResponseResult.success("更新成功");
    }

    @GetMapping("/demo3")
    @ResponseBody
    public void demo3(HttpServletRequest request) {
        System.out.println(request.getHeader("myHeader"));
        for (Cookie cookie : request.getCookies()) {
            if ("myCookie".equals(cookie.getName())) {
                System.out.println(cookie.getValue());
            }
        }
    }













}
