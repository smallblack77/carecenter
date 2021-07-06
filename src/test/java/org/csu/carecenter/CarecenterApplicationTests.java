package org.csu.carecenter;

import org.csu.carecenter.entity.User;
import org.csu.carecenter.service.UserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("org.csu.carecenter.Persistence")
class CarecenterApplicationTests {

    @Autowired
    UserService userService ;

    @Test
    void contextLoads() {
    }

    @Test
    void testUser(){
        User user = userService.getUserByUserIdAndPassword("1","2333");
        System.out.println(user.getUserId()+" "+user.getEmail()+" "+user.getPhoneNumber());
    }

}
