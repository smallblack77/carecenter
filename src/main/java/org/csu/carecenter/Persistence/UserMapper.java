package org.csu.carecenter.Persistence;


import org.csu.carecenter.entity.User;
import org.springframework.stereotype.Repository;

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

}
