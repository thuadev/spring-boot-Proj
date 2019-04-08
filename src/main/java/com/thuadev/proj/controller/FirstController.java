package com.thuadev.proj.controller;

import com.thuadev.proj.pojo.UserEntity;
import com.thuadev.proj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @Autowired
    public UserService userService;
    @RequestMapping(value = "/getName",method = RequestMethod.GET)
    public String getName(){
        return "你好";
    }
    @RequestMapping(value = "/getUser/{id}",method = RequestMethod.GET)
    public UserEntity getUser(@PathVariable("id") Long id){
        return userService.findUserEntityById(id);
    }
    @RequestMapping(method = RequestMethod.POST)
    public String addUser(UserEntity userEntity){
        userService.addUser(userEntity);
        return "新增成功";
    }
}
