package com.cn.liu.mapper;

import com.cn.liu.entity.Login;
import com.cn.liu.entity.User;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.Resource;
import java.util.List;

@Mapper
public interface UserMapper {
    public int insertUser(User user);
    public int deleteUser(User user);
    public User selectUserById(int id);
    public int selectUserByname(User user);
    public int update(User user);
    public List<User> queryForList();
    public User login(Login login);
}
