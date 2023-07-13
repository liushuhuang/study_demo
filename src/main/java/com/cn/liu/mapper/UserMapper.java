package com.cn.liu.mapper;

import com.cn.liu.entity.Login;
import com.cn.liu.entity.User;
import com.cn.liu.entity.User1;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author liu
 */
@Mapper
public interface UserMapper {
    int deleteUser(int id);
    User selectUserById(int id);
    int update(User user);
    List<User> queryForList();
    User login(Login login);
    int resgity(User user);

    int updatepwd(Map<String,String> newpwd);


    User1 selectUserToUser1(int id);
}
