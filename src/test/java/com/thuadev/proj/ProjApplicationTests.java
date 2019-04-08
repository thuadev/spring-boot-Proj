package com.thuadev.proj;

import com.thuadev.proj.pojo.UserEntity;
import com.thuadev.proj.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjApplicationTests {
    @Autowired
    private UserService userService;
    @Test
    public void contextLoads() {
    }
    @Test
    public void addUser(){
        UserEntity userEntity = new UserEntity();
        Date date = new Date();
        userEntity.setCreated(date);
        userEntity.setUpdated(date);
        userEntity.setUserName("小D");
        userService.addUser(userEntity);
        long id = userService.findMaxUserId();
        System.out.println(id);
    }
}
