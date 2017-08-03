package com.champion.service.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.champion.service.sqlmanager.UserSqlManger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yongjie.pei on 2017/8/3.
 */
@Service
public class UserServiceImpl implements UserService{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserSqlManger userSqlManger;

    /**
     * 新增
     * <br>------------------------------<br>
     * @param userJson
     */
    public void insert(String userJson) {
        userSqlManger.insert(userJson);
        logger.info("成功");
    }

}
