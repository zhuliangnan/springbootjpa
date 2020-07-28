package com.zln.springbootjpa.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 权限类
 * @Auther:http://www.jxysgzs.cn
 * @Date:2020/2/5
 * @Description:cn.jxysgzs.springdatajpa.pojo
 * @Version:1.0
 */
@Entity
@Table(name="t_jurisdiction")
public class Jurisdiction implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="jurisdictionId")
    private Integer jurisdictionId;
    @Column(name="jurisdictionName")
    private String jurisdictionName;

    @JsonIgnoreProperties("jurisdiction")
    @ManyToMany(mappedBy="jurisdiction") //权限和角色多对多
    private Set<Role> roles =new HashSet<>();

    public Integer getJurisdictionId() {
        return jurisdictionId;
    }

    public void setJurisdictionId(Integer jurisdictionId) {
        this.jurisdictionId = jurisdictionId;
    }

    public String getJurisdictionName() {
        return jurisdictionName;
    }

    public void setJurisdictionName(String jurisdictionName) {
        this.jurisdictionName = jurisdictionName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

