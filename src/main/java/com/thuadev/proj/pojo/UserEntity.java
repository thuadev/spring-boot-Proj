package com.thuadev.proj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;
import org.springframework.context.annotation.Lazy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
@Proxy(lazy = false)
@Entity
@Table(name = "USER_INFO")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class UserEntity extends BaseEntity implements Serializable {

    @Column
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
