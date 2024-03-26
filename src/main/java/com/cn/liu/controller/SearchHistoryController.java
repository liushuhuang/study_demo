package com.cn.liu.controller;

import com.cn.liu.entity.AjaxResult;
import com.cn.liu.entity.User;
import com.cn.liu.service.SearchHistoryService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liushuhuang
 * @date 2024/3/26
 */
@RestController
public class SearchHistoryController {

    @Resource
    SearchHistoryService searchHistoryService;

    @RequestMapping("/search")
    public AjaxResult search(@RequestBody User user){
        searchHistoryService.search(user.getName());
        return AjaxResult.success("sucess:"+user.getName());
    }

    @RequestMapping("/history")
    public AjaxResult history(){
        return AjaxResult.success(searchHistoryService.history());
    }
}
