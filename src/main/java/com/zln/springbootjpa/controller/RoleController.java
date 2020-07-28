package com.zln.springbootjpa.controller;


import com.zln.springbootjpa.dao.RoleJpaDao;
import com.zln.springbootjpa.model.Jurisdiction;
import com.zln.springbootjpa.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @Auther:http://www.jxysgzs.cn
 * @Date:2020/2/5
 * @Description:cn.jxysgzs.springdatajpa.controller
 * @Version:1.0
 *
 *
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleJpaDao roleJpaDao;

    /**
     *
     * 多对多关系  给角色赋予权限
     *
     * */
    @GetMapping("/insertRole")
    public Role insertRole(){
        //先将数据库中第二条管理员拿出来
        Role role=new Role();
        role.setRoleName("管理员");
        role.setRoleId(2);
        //将数据库中踢人及禁言功能给予管理员角色
        Jurisdiction jurisdiction1=new Jurisdiction();
        jurisdiction1.setJurisdictionId(1);
        jurisdiction1.setJurisdictionName("踢人");
        Jurisdiction jurisdiction2=new Jurisdiction();
        jurisdiction2.setJurisdictionId(4);
        jurisdiction2.setJurisdictionName("禁言");
        Set<Jurisdiction> set=new HashSet<>();
        set.add(jurisdiction1);
        set.add(jurisdiction2);
        //添加入角色
        role.setJurisdiction(set);

        //这里我说明一下，这里我们用到的是修改的能力，因为之前没考虑好，把命名成Insert了
        //抱歉，总之这里是把数据库表t_Role中管理员加上权限。
        return this.roleJpaDao.save(role);
    }

    @GetMapping("/selectHqlById")
    public List<Role> selectHqlById(@RequestParam("roleId")Integer roleId){
        return this.roleJpaDao.queryByIdUseHql02(roleId);
    }
    @GetMapping("/deleteHqlById")
    public String deleteHqlById(@RequestParam("roleId")Integer roleId){
        this.roleJpaDao.deleteById(roleId);
        return "删除成功";
    }

    @GetMapping("/selectSqlById")
    public List<Role> selectSqlById(@RequestParam("roleId")Integer roleId){
        return this.roleJpaDao.queryByIdUseSql(roleId);
    }

}

