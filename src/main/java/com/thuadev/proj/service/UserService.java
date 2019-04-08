package com.thuadev.proj.service;

import com.thuadev.proj.pojo.UserEntity;
import com.thuadev.proj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Resource
    private EntityManagerFactory entityManagerFactory;
    public UserEntity findUserEntityById(Long id){
        return userRepository.getOne(id);
    }
    public UserEntity addUser(UserEntity userEntity){
        return userRepository.save(userEntity);
    }
    public long findMaxUserId(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = criteriaBuilder.createQuery(Long.class);
        Root<UserEntity> root = cq.from(UserEntity.class);
        cq.select(criteriaBuilder.greatest((Path)root.get("id")));
        TypedQuery<Long> typedQuery = entityManager.createQuery(cq);
        return typedQuery.getSingleResult();
    }
}
