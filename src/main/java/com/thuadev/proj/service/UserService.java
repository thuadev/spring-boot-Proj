package com.thuadev.proj.service;

import com.thuadev.proj.pojo.UserEntity;
import com.thuadev.proj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

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
    public Page<UserEntity> findByCondition(UserEntity userEntity, Pageable pageable){
        return userRepository.findAll((root, query, cb)->{
            List<Predicate> predicates = new ArrayList<Predicate>();
            if(!StringUtils.isEmpty(userEntity.getUserName())){
                predicates.add(cb.like(root.get("userName"),"%"+userEntity.getUserName()));
            }
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        },pageable);
    }
    public UserEntity findTopByCreated(){
        return userRepository.findFirstByOrderByCreatedDesc();
    }
}
