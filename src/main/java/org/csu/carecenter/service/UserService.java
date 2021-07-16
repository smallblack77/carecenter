package org.csu.carecenter.service;

import org.csu.carecenter.Persistence.UserMapper;
import org.csu.carecenter.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    //登录
    public User getUserByUserIdAndPassword(int userId,String password){
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

    //获取所有user
    public List<User> getAllUser(){
        return userMapper.getAllUser();
    }

    //删除user
    public void deleteUser(int userId){
        userMapper.deleteUser(userId);
    }

    //删除signon表中的user
    public void deleteSignon(int userId){
        userMapper.deleteSignon(userId);
    }

    //修改user表
    public void updateUser(User user){
        userMapper.updateUser(user);
    }

    //修改Signon表
    public void updateSignon(User user){
        userMapper.updateSignon(user);
    }

    //通过UserId获取User信息
    public User getUserByUserId(int userId){
        return userMapper.getUserByUserId(userId);
    }

    //获取空数据
    public List<Integer> getNullInfo(){
        return userMapper.getNullInfo();
    }

}
