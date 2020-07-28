package com.zln.springbootjpa.dao;

import com.zln.springbootjpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserJpaDaoTest extends JpaRepository<User, Integer> {
    User findByUsernameAndPssword(String userName, String userPwd);

    List<User> findByUsernameContaining(String userName);

}
