package com.zln.springbootjpa.model;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * @Auther:http://www.jxysgzs.cn
 * @Date:2020/2/1
 * @Description:cn.jxysgzs.springdatajpa.pojo
 * @Version:1.0
 */
/**
 * 角色类
 *
 * @author a2417
 *
 */
@Entity
@Table(name="t_role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="roleId")
    private Integer roleId;
    @Column(name="roleName")
    private String roleName;
    //fetch:懒加载
    @JsonIgnoreProperties("role")
    @OneToMany(mappedBy="role",fetch=FetchType.EAGER)
    //mappedBy参数是指应该指向从表中维护与主表关系的字段
    private Set<User> usersList=new HashSet<>();

    @JsonIgnoreProperties("roles")
    @ManyToMany(cascade=CascadeType.PERSIST)
    //@JoinTable:**映射中间表**,此注解在于多对多时只要添加到两个Class中的一个就行
    //joinColumns:当前表当中的主键连接中间表的外键,中间表有两个外键
    @JoinTable(name="t_role_jurisdiction",joinColumns=@JoinColumn(name="roleId"),inverseJoinColumns=@JoinColumn(name="jurisdictionId"))
    private Set<Jurisdiction> jurisdiction=new HashSet<>();


    public Set<Jurisdiction> getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(Set<Jurisdiction> jurisdiction) {
        this.jurisdiction = jurisdiction;
    }
    public Set<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(Set<User> usersList) {
        this.usersList = usersList;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}

