package com.cn.liu.service;

import java.util.List;

/**
 * @author liushuhuang
 * @date 2024/3/26
 */
public interface SearchHistoryService {
    public void search(String s);
    public List<String> history();
}
