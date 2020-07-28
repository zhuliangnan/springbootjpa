package com.zln.springbootjpa.dao;

import com.zln.springbootjpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserJpaDao extends JpaRepository<User, Integer> , JpaSpecificationExecutor<User>  {

    User findByUsernameAndPssword(String userName,String userPwd);

    List<User> findByUsernameContaining(String userName);



}
