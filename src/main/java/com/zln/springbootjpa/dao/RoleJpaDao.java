package com.zln.springbootjpa.dao;

import com.zln.springbootjpa.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleJpaDao extends JpaRepository<Role,Integer> , JpaSpecificationExecutor<Role> {

    //方式一：按位置录入参数
    @Query(value = "SELECT p FROM Role p WHERE p.roleId= ?1")
    List<Role> queryByIdUseHql01(Integer roleId);

    /*
    * 语句中Role 是类的名称，并不是数据库中的表名
      语句中 ?1 代表的是下方抽象方法中第1个参数 ?2代表第二个，以此类推。*/

    //方式二：按名称录入参数
    @Query(value = "SELECT p FROM Role p WHERE p.roleId= :roleId")
    List<Role> queryByIdUseHql02(@Param("roleId") Integer roleId);

/*  在@Query注解中编写JPQL实现DELETE和UODATE操作的时候必须加@Modifying注解，以通知Springdata这是一个DELETE或UPDATE操作。
    UPDATE或DELETE操作需要使用事务，此时需要定义Service层，在Service层的方法上添加事务操作。
    注意JPQL不支持INSERT操作*/

    //删除
    @Query(value = "DELETE  FROM Role p WHERE p.roleId= :roleId")
    @Modifying
    void deleteById(@Param("roleId") Integer roleId);

    //执行对应Sql语句
    @Query(value = "select * from t_role where role_id = ?",nativeQuery = true)
    List<Role> queryByIdUseSql(Integer roleId);


}
