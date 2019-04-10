package com.thuadev.proj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

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
