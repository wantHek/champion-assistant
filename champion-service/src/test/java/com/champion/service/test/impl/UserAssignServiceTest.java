package com.champion.service.test.impl;

import com.champion.service.service.UserService;
import com.champion.service.test.BaseJunit4Test;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yongjie.pei on 2017/7/31.
 */
public class UserAssignServiceTest  extends BaseJunit4Test {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired  //自动注入,默认按名称
    private UserService userService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test   //标明是测试方法
    public void indexCreate( ) throws Exception {
        IndexOperations io=mongoTemplate.indexOps("md5Index");
        Index index =new Index();
        index.on("md5Index", Sort.Direction.ASC); //为name属性加上 索引
        index.unique();//唯一索引
        index.named("md5Index_unique");
        io.ensureIndex(index);
    }

    @Test   //标明是测试方法
//    @Transactional   //标明此方法需使用事务
//    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void insert( ) throws Exception {
        String userJson = "{\"userName\":\"champion2\",\"passWord\":\"champion2\"}";
        String registered = userService.doWork(userJson, "registered");
        logger.info("----------------插入成功或失败："+registered);
    }

    @Test   //标明是测试方法
//    @Transactional   //标明此方法需使用事务
//    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void findAll( ) throws Exception {
        String registered = userService.doWork(null, "findAll");
        logger.info("----------------插入成功或失败："+registered);
    }
}
