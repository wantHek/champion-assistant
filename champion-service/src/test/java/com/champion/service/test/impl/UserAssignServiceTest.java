package com.champion.service.test.impl;

import com.champion.service.dao.UserDao;
import com.champion.service.test.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yongjie.pei on 2017/7/31.
 */
public class UserAssignServiceTest  extends BaseJunit4Test {


    @Autowired  //自动注入,默认按名称
    private UserDao userDao;

    @Test   //标明是测试方法
    @Transactional   //标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void insert( ) {
        String userJson = "{\"username\":\"BeJson\",\"userpass\":\"BeJson\"}";
        userDao.insert(userJson);
    }
}
