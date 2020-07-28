package com.zln.springbootjpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private int uid;
    @Column(name = "userName")
    private String username;
    @Column(name = "password")
    private String pssword;
    /*
    *
    * CascadeType.PERSIST：级联持久化（保存）操作（持久保存拥有方实体时，也会持久保存该实体的所有相关数据。）
      CascadeType.REMOVE ：级联删除操作。删除当前实体时，与它有映射关系的实体也会跟着被删除。
      CascadeType.MERGE：级联更新（合并）操作。当Student中的数据改变，会相应地更新Course中的数据。
      CascadeType.DETACH：级联脱管/游离操作。如果你要删除一个实体，但是它有外键无法删除，你就需要这个级联权限了。它会撤销所有相关的外键关联。
      CascadeType.REFRESH：级联刷新操作
      CascadeType.ALL：拥有以上所有级联操作权限。
    * */
    @ManyToOne(cascade=CascadeType.PERSIST)  //多对一的注解 cascade 参数是给予他各种联级操作的权限，
    private Role role;

}
