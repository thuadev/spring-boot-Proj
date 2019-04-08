package com.thuadev.proj.service;

import com.thuadev.proj.pojo.UserEntity;
import com.thuadev.proj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public UserEntity findUserEntityById(Long id){
        return userRepository.getOne(id);
    }
    public UserEntity addUser(UserEntity userEntity){
        return userRepository.save(userEntity);
    }
}
