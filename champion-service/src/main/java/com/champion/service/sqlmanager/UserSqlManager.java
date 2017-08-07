package com.champion.service.sqlmanager;

import com.champion.service.models.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yongjie.pei on 2017/8/3.
 */
@Component
public class UserSqlManager {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 新增
     * <br>------------------------------<br>
     * @param userModel
     */
    public UserModel insert(UserModel userModel) {
        mongoTemplate.insert(userModel,"user");
        return userModel;
    }

    /**
     * 查询所有数据
     *
     * @author：tuzongxun
     * @Title: findAll
     * @Description: TODO
     * @param @return
     * @date May 13, 2016 3:10:29 PM
     * @throws
     */
    public List<UserModel> findAll() {
        // 需要设置集合对应的尸体类和相应的集合名，从而查询结果直接映射
        List<UserModel> userList = mongoTemplate.findAll(UserModel.class,
                "user");
        return userList;
    }

    /**
     * 按条件删除数据
     *
     * @author：tuzongxun
     * @Title: removeUser
     * @Description: TODO
     * @param @param userName
     * @date May 13, 2016 3:11:01 PM
     * @throws
     */
    public void removeUser(String userName) {
        // 设置删除条件，如果条件内容为空则删除所有
        Query query = new Query();
        Criteria criteria = new Criteria("userName");
        criteria.is(userName);
        query.addCriteria(criteria);
        mongoTemplate.remove(query, "user");
    }

    /**
     * 修改数据
     *
     * @author：tuzongxun
     * @Title: updateUser
     * @Description: TODO
     * @param @param user
     * @date May 13, 2016 3:11:12 PM
     * @throws
     */
    public void updateUser(UserModel user) {
        // 设置修改条件
        Query query = new Query();
        Criteria criteria = new Criteria("userName");
        criteria.is(user.getUserName());
        query.addCriteria(criteria);
        // 设置修改内容
        Update update = Update.update("password", user.getPassWord());
        // 参数：查询条件，更改结果，集合名
        mongoTemplate.updateFirst(query, update, "user");
    }

    /**
     * 根据条件查询
     *
     * @author：tuzongxun
     * @Title: findForRequery
     * @Description: TODO
     * @param @param userName
     * @date May 13, 2016 4:08:15 PM
     * @throws
     */
    public List<UserModel> findForRequery(String userName) {
        Query query = new Query();
        Criteria criteria = new Criteria("userName");
        criteria.is(userName);
        query.addCriteria(criteria);
        // 查询条件，集合对应的实体类，集合名
        List<UserModel> userList = mongoTemplate.find(query, UserModel.class,
                "user");
        return userList;
    }
}
