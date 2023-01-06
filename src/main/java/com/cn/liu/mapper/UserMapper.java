package com.cn.liu.mapper;

import com.cn.liu.entity.Login;
import com.cn.liu.entity.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    int deleteUser(int id);
    User selectUserById(int id);
    int update(User user);
    List<User> queryForList();
    User login(Login login);
    int resgity(User user);

    int updatepwd(Map<String,String> newpwd);
}
