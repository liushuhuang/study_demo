package com.cn.liu.controller;


import com.alibaba.fastjson.JSON;
import com.cn.liu.Json.ResponseResult;
import com.cn.liu.entity.Login;
import com.cn.liu.entity.User;
import com.cn.liu.exception.BusinessException;
import com.cn.liu.mapper.UserMapper;
import com.cn.liu.util.ImageCaptchaParams;
import com.cn.liu.util.ImageCaptchaUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author liu
 */
@Controller

public class LoginController {
    public final UserMapper userMapper;
    public final String pre1 = "dyms"+"_"+"imageCaptchaCode"+"_";
    public final String pre2 = "dyms"+"_"+"captchaCode"+"_";

    @Autowired
    private RedisTemplate redisTemplate;


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


    @GetMapping("/excel")
    @ResponseBody
    public void excel(HttpServletResponse response) throws IOException {
        String path = "D:\\demo";
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("ssss");
        FileOutputStream fileOutputStream = new FileOutputStream(path + "1.xlsx");
        workbook.write(fileOutputStream);

    }

    @PostMapping ("/t1")
    @ResponseBody
    public ResponseResult t1(@RequestBody Map<String,String> pwdmap){
        User user= userMapper.selectUserById(Integer.parseInt(pwdmap.get("id")));
        System.out.println(pwdmap.get("nowpwd"));
        System.out.println(user.getPwd());
        if(!user.getPwd().equals(pwdmap.get("nowpwd"))){
            throw new BusinessException("当前密码不正确");
        }
        if(!pwdmap.get("pwd").equals(pwdmap.get("repwd"))){
            throw new BusinessException("两次密码输入不一致");
        }
        userMapper.updatepwd(pwdmap);
        return ResponseResult.success("修改密码成功");


    }

    @PostMapping ("/tt")
    @ResponseBody
    public void tt(@RequestBody Map<String,Object> phonemap,HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        String captchaCode = ImageCaptchaUtil.create(new ImageCaptchaParams(),outputStream);
        String phone = (String) phonemap.get("phone");
        String key = pre1+phone;
        redisTemplate.boundValueOps(key).set(captchaCode,1, TimeUnit.MINUTES);

    }


    @PostMapping("/tt1")
    @ResponseBody
    public String tt1(@RequestBody Map<String,Object> sent) throws IOException {
        String phone = (String) sent.get("phone");
        String imageCaptchaCode1 = (String) sent.get("imageCaptchaCode");
        String key1 = pre1+phone;
        String imageCaptchaCode = (String) redisTemplate.boundValueOps(key1).get();
        String key2 = pre2+phone;
        if(imageCaptchaCode.equalsIgnoreCase(imageCaptchaCode1)){
            redisTemplate.boundValueOps(key2).set("1234",1, TimeUnit.MINUTES);
            return "1234";
        }
        else {
            throw new BusinessException();
        }
    }

    @PostMapping("/tt2")
    @ResponseBody
    public String tt2(@RequestBody Map<String,Object> login) throws IOException {
        String phone = (String) login.get("phone");
        String captchaCode1 = (String) login.get("captchaCode");
        String key = pre2+phone;
        String imageCaptchaCode = (String) redisTemplate.boundValueOps(key).get();
        if(imageCaptchaCode.equals(captchaCode1)){
            return "sucess";
        }
        else {
            throw new BusinessException();
        }
    }








}
