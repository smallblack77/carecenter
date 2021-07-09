package org.csu.carecenter.Persistence;


import org.csu.carecenter.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    //登录时通过Id和password获得usser
    User getUserByUserIdAndPassword(User user);

    //注册时新增User
    void insertUser(User user);

    //新增信息到signon表
    void insertSignon(User user);

    //获得userId并返回
    int getMaxUserId();

    //获取所有user
    List<User> getAllUser();

    //User删除
    void deleteUser(int userId);

    //signon表中删除
    void deleteSignon(int userId);

    //修改User表
    void updateUser(User user);

    //修改Signon表
    void updateSignon(User user);

    //通过userId获取User信息
    User getUserByUserId(int userId);

}
