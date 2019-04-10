package com.thuadev.proj.controller;

import com.thuadev.proj.pojo.UserEntity;
import com.thuadev.proj.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class FirstController {
    @Autowired
    public UserService userService;
    private Logger logger = LoggerFactory.getLogger(FirstController.class);
    @RequestMapping(value = "/getName",method = RequestMethod.GET)
    public String getName(){
        return "你好";
    }
    @RequestMapping(value = "/getUser/{id}",method = RequestMethod.GET)
    public UserEntity getUser(@PathVariable("id") Long id){
        return userService.findUserEntityById(id);
    }
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public UserEntity addUser(){
        UserEntity userEntity = new UserEntity();
        Date date = new Date();
        userEntity.setCreated(date);
        userEntity.setUpdated(date);
        userEntity.setUserName("小D");
        return userService.addUser(userEntity);
    }
    @RequestMapping(value = "/lastUser", method = RequestMethod.GET)
    public UserEntity lastUser(){
        return userService.findTopByCreated();
    }
    @RequestMapping(value = "/getUsers/{userName}", method = RequestMethod.GET)
    public List<UserEntity> getUsers(@PathVariable("userName")String userName){
        logger.debug("获取用户信息列表" + userName);
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userName);
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        int pageSize = 10;
        int pageNo =0;
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<UserEntity> page = userService.findByCondition(userEntity, pageable);
        List<UserEntity> userEntities = new ArrayList<>();
        for (UserEntity user :page){
            userEntities.add(user);
        }
        return userEntities;
    }
}
