package com.thuadev.proj.service;

import com.thuadev.proj.pojo.UserEntity;
import com.thuadev.proj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Resource
    private EntityManagerFactory entityManagerFactory;
    public UserEntity findUserEntityById(Long id){
        UserEntity userEntity = null;
//        /*方法1、使用findById进行查询，查询结果为空时，需要对optional对象进行判断**/
//        Optional<UserEntity> option = userRepository.findById(id);
//        if(option.isPresent()){
//            userEntity = option.get();
//        }
//        /*方法2、使用自定义查询findUserEntityById进行查询，查询结果为空时，自动返回一个空的对象**/
//        userEntity = userRepository.findUserEntityById(id);
//        /*方法3、使用getOne()进行查询，查询结果为空是会抛出异常，需要进行异常处理**/
//        try {
//            userEntity = userRepository.getOne(id);
//        } catch (JpaObjectRetrievalFailureException e) {
//            e.printStackTrace();
//        }
        /* 方法4、使用findOne进行查询，需传入example对象，通过userEntity对象进行构造，返回值处理与findById处理类似**/
        userEntity  = new UserEntity();
        userEntity.setId(id);
        Example<UserEntity> example = Example.of(userEntity);
        Optional<UserEntity> option1 = userRepository.findOne(example);
        if(option1.isPresent()){
            userEntity = option1.get();
        }else {
            userEntity = null;
        }
        return userEntity;
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
