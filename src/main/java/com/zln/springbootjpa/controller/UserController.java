package com.zln.springbootjpa.controller;


import com.zln.springbootjpa.dao.RoleJpaDao;
import com.zln.springbootjpa.dao.UserDaoSortAndPaging;
import com.zln.springbootjpa.dao.UserJpaDao;
import com.zln.springbootjpa.model.Role;
import com.zln.springbootjpa.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserJpaDao userJpaDao;
    private final RoleJpaDao roleJpaDao;


    private final UserDaoSortAndPaging userDaoSortAndPaging;

    public UserController(UserJpaDao userJpaDao, UserDaoSortAndPaging userDaoSortAndPaging, RoleJpaDao roleJpaDao) {
        this.userJpaDao = userJpaDao;
        this.userDaoSortAndPaging = userDaoSortAndPaging;
        this.roleJpaDao = roleJpaDao;
    }


    /**
     * 增加一条
     *
     * @param userName
     * @param userName
     * @param userPwd
     * @return
     */
    @GetMapping("/insertUser")
    public User insertUser(@RequestParam("userName") String userName, @RequestParam("userPwd") String userPwd) {
        User users = new User();
        users.setUsername(userName);
        users.setPssword(userPwd);
        return userJpaDao.save(users);
    }

    /**
     * 修改
     *
     * @param userId
     * @param userName
     * @param userPwd
     * @return
     */
    @GetMapping("/updateUser")
    public User updateUser(@RequestParam("userId") Integer userId, @RequestParam("userName") String userName, @RequestParam("userPwd") String userPwd) {
        User users = new User();
        users.setUsername(userName);
        users.setPssword(userPwd);
        users.setUid(userId);
        Role role = new Role();
        role.setRoleId(1);
        role.setRoleName("群众");
        users.setRole(role);
        return userJpaDao.save(users);
    }


    /**
     * 删除
     *
     * @param userId 通过主键删除 不是通过id
     * @return
     */
    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") Integer userId) {
        this.userJpaDao.deleteById(userId);
        return "删除成功";
    }


    /**
     * 主键查询
     *
     * @return
     */
    @GetMapping("/selectUserId")
    public User selectUserId(@RequestParam("userId") Integer userId) {
        Optional<User> users = userJpaDao.findById(userId);
        return users.get();
    }


    /**
     * 用户名密码查询  findByUsernameAndPassword 这里的对应关系 严格对应属性 而不是 表的字段名
     *
     * @return
     */
    @GetMapping("/findByUsernameAndPassword")
    public User findByUsernameAndPassword(@RequestParam("userName") String userName, @RequestParam("userPwd") String userPwd) {
        User users = userJpaDao.findByUsernameAndPssword(userName, userPwd);
        return users;
    }


    /**
     * 用户名模糊查询  findByUsernameContaining
     *
     * @return
     */
    @GetMapping("/findByUsernameContaining")
    public List<User> findByUsernameContaining(@RequestParam("userName") String userName) {
        List<User> userList = userJpaDao.findByUsernameContaining(userName);
        return userList;
    }


    /**
     * 按条件分页排序查询
     *
     * @param userName 条件 用户名
     * @param pageNum  页码
     * @param pageSize 一页展示的条数
     * @return
     */
    @GetMapping("/selectUserList")
    public Page selectUserList(@RequestParam("userName") String userName, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        Specification<User> spec = new Specification<User>() {
            /**
             * 匿名内部类
             * Predicate:封装了单个的查询条件
             * Root<Users> root:查询对象的属性的封装
             * CriteriaQuery<?> query:封装了我们要执行的查询中的各个部分的信息
             * CriteriaBuilder cb:查询条件的构造器。定义不同的查询条件
             */
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //这里用一个list，主要是为了将不同的条件分别封装起来，最后在组合成一个，有利于管理，如果只有一个条件，不用这个也可以
                List<Predicate> predicates = new ArrayList<>();
                if (userName != null) {
                    //这里使用CD更像是拼接的意思，and就是在SQL后面增加一个and ，其他的雷同
                    //查询条件例子：where name ='李健'
                    //参数一：查询条件的属性     参数二：条件值
                    predicates.add(cb.and(cb.equal(root.get("username"), userName)));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

        //Order 定义排序规则
        //Sort对象封装了排序规则

        //Pageable 封装了分页的参数，当前页，每页显示的条数，注意：它的当前页是从0开始的。
        //PageRequest(0, 2) Page：当前页 。 size：每页显示的条数
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Direction.DESC, "uid"));
        //封装分页信息的返回值，看不懂可以输出一下看看或者看看源码很简单
        Page<User> page = this.userJpaDao.findAll(spec, pageable);
        return page;
    }

    /**
     * 主键查询Role  一对多
     * @return
     */
    @GetMapping("/selectRoleId")
    public Role selectRoleId(@RequestParam("roleId")Integer roleId){
        Optional<Role> role=this.roleJpaDao.findById(roleId);
        return role.get();
    }



}
