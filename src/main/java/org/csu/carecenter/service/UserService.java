package org.csu.carecenter.service;

import org.csu.carecenter.Persistence.UserMapper;
import org.csu.carecenter.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    //登录
    public User getUserByUserIdAndPassword(String userId,String password){
        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        return userMapper.getUserByUserIdAndPassword(user);
    }

    //注册 新增用户到user表
    public void insertUser(User user){
        userMapper.insertUser(user);
    }

    //将信息添加进signon表
    public void insertSignon(User user){
        userMapper.insertSignon(user);
    }

    //获得对应的userId并返回
    public int getMaxUserId(){
        return userMapper.getMaxUserId();
    }

}
