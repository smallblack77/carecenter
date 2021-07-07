package org.csu.carecenter;

import org.csu.carecenter.entity.Customer;
import org.csu.carecenter.entity.User;
import org.csu.carecenter.service.CustomerService;
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

    @Autowired
    CustomerService customerService;

    @Test
    void contextLoads() {
    }

    @Test
    void testCustomer(){
//        Customer customer = customerService.getCustomer("张三");
//        System.out.println(customer.getSex()+" "+customer.getHeight()+" "+customer.getBirthday());
        Customer customer = new Customer();
        customer.setId("2");
        customer.setName("张思");
        customer.setSex("男");
        customer.setAge(56);
        customer.setBirthday("1965-2-7");
        customer.setHeight(186.0);
        customer.setWeight(130.2);
        customer.setBedNum(1);
        customer.setAttention("花生过敏");
        customerService.addCustomer(customer);
    }

    @Test
    void testUser(){
        User user = userService.getUserByUserIdAndPassword("1","2333");
        System.out.println(user.getUserId()+" "+user.getEmail()+" "+user.getPhoneNumber());
    }

}
