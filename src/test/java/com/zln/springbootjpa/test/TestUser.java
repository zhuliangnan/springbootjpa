package com.zln.springbootjpa.test;


import com.zln.springbootjpa.SpringbootjpaApplication;
import com.zln.springbootjpa.dao.UserDaoSortAndPaging;
import com.zln.springbootjpa.dao.UserJpaDaoTest;
import com.zln.springbootjpa.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SpringbootjpaApplication.class})
public class TestUser {


    @Autowired
    private UserJpaDaoTest userJpaDaoTest;

    @Autowired
    private UserDaoSortAndPaging userDaoSortAndPaging;


    /**
     * 用户名模糊查询  findByUsernameContaining
     *
     * @return
     */
    @Test
    public void findByUsernameContaining() {
        List<User> userList = userJpaDaoTest.findByUsernameContaining("朱");
        System.out.println(userList.toString());
    }



/**
     * 排序测试
/*     */

    @Test
    public void testPagingAndSortingRepository() {
        //Order 定义排序规则
        //参数一：排序方式：Direction.DESC倒叙
        //参数二：按照哪个字段来排序
        List<User> list = (List<User>) this.userDaoSortAndPaging.findAll(Sort.by(Sort.Direction.DESC, "uid"));
        for (User user : list) {
            System.out.println(user.toString());
        }
    }


/**
     * 分页测试 返回当前的查询的结果
     */

    @Test
    public void testPagingAndSortingRepositoryPaging(){
        //Pageable 封装了分页的参数，当前页，每页显示的条数，注意：它的当前页是从0开始的。
        //PageRequest(0, 2) Page：当前页 。 size：每页显示的条数

        Page<User> page=this.userDaoSortAndPaging.findAll(PageRequest.of(0,2));
        System.out.println("总条数:"+page.getTotalElements());
        System.out.println("总页数:"+page.getTotalPages());
        System.out.println("总...数:"+page.getNumberOfElements());
        List<User> list =page.getContent();
        for(User user:list){
            System.out.println(user.toString());
        }
    }

/*


    */
/**
     * 排序加分页测试  DESC 降序
     */

    @Test
    public void testPagingAndSortingRepositoryPagingSort(){
        //Pageable 封装了分页的参数，当前页，每页显示的条数，注意：它的当前页是从0开始的。
        //PageRequest(0, 2) Page：当前页 。 size：每页显示的条数
        Pageable pageable=PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC,"uid"));
        Page<User> page=this.userDaoSortAndPaging.findAll(pageable);
        System.out.println("总条数:"+page.getTotalElements());
        System.out.println("总页数:"+page.getTotalPages());
        System.out.println("总...数:"+page.getNumberOfElements());
        List<User> list =page.getContent();
        for(User user:list){
            System.out.println(user.toString());
        }
    }





}
