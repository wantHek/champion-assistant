package com.champion.service.sqlmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by yongjie.pei on 2017/8/3.
 */
@Component
public class UserSqlManger {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 新增
     * <br>------------------------------<br>
     * @param userJson
     */
    public void insert(String userJson) {
        mongoTemplate.insert(userJson);
        logger.info("成功");
    }
}
