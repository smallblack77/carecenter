package org.csu.carecenter.service;

import org.csu.carecenter.Persistence.UserMapper;
import org.csu.carecenter.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserByUserIdAndPassword(String userId,String password){
        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        return userMapper.getUserByUserIdAndPassword(user);
    }
}
