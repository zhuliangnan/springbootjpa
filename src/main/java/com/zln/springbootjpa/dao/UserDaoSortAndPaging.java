package com.zln.springbootjpa.dao;

import com.zln.springbootjpa.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDaoSortAndPaging extends PagingAndSortingRepository<User, Integer> {

}
