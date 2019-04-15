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

import javax.persistence.EntityNotFoundException;
import java.util.*;

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
    public Map<String, Object> getUser(@PathVariable("id") Long id){
        Map<String, Object> resultMap = new HashMap<>();
        UserEntity userEntity = userService.findUserEntityById(id);
        if (userEntity == null){
            resultMap.put("count", 0);
            resultMap.put("objects", null);
        }else {
            resultMap.put("count",1);
            resultMap.put("objects", userEntity);
        }
        return resultMap;

    }
    @RequestMapping(value = "/addUser/{userName}", method = RequestMethod.GET)
    public UserEntity addUser(@PathVariable("userName")String userName){
        UserEntity userEntity = new UserEntity();
        Date date = new Date();
        userEntity.setCreated(date);
        userEntity.setUpdated(date);
        userEntity.setUserName(userName);
        return userService.addUser(userEntity);
    }
    @RequestMapping(value = "/lastUser", method = RequestMethod.GET)
    public UserEntity lastUser(){
        logger.info("查询最新客户 ");
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
