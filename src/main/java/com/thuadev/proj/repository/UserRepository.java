package com.thuadev.proj.repository;

import com.thuadev.proj.pojo.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaSpecificationExecutor<UserEntity>,JpaRepository<UserEntity,Long> {
    UserEntity findFirstByOrderByCreatedDesc();
    UserEntity findUserEntityById(Long id);
}
