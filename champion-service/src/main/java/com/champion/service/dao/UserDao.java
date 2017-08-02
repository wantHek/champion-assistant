package com.champion.service.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by yongjie.pei on 2017/7/31.
 */
@Repository
public class UserDao {
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

//    /**
//     * 批量新增
//     * <br>------------------------------<br>
//     * @param users
//     */
//    public void insertAll(List<User> users) {
//        mongoTemplate.insertAll(users);
//    }
//
//    /**
//     * 删除,按主键id, 如果主键的值为null,删除会失败
//     * <br>------------------------------<br>
//     * @param id
//     */
//    public void deleteById(String id) {
//        User user = new User(id, null, 0);
//        mongoTemplate.remove(user);
//    }
//
//    /**
//     * 按条件删除
//     * <br>------------------------------<br>
//     * @param criteriaUser
//     */
//    public void delete(User criteriaUser) {
//        Criteria criteria = Criteria.where("age").gt(criteriaUser.getAge());;
//        Query query = new Query(criteria);
//        mongoTemplate.remove(query, User.class);
//    }
//
//    /**
//     * 删除全部
//     * <br>------------------------------<br>
//     */
//    public void deleteAll() {
//        mongoTemplate.dropCollection(User.class);
//    }
//
//    /**
//     * 按主键修改,
//     * 如果文档中没有相关key 会新增 使用$set修改器
//     * <br>------------------------------<br>
//     * @param user
//     */
//    public void updateById(User user) {
//        Criteria criteria = Criteria.where("id").is(user.getId());
//        Query query = new Query(criteria);
//        Update update = Update.update("age", user.getAge()).set("name", user.getName());
//        mongoTemplate.updateFirst(query, update, User.class);
//    }
//
//    /**
//     * 修改多条
//     * <br>------------------------------<br>
//     * @param criteriaUser
//     * @param user
//     */
//    public void update(User criteriaUser, User user) {
//        Criteria criteria = Criteria.where("age").gt(criteriaUser.getAge());;
//        Query query = new Query(criteria);
//        Update update = Update.update("name", user.getName()).set("age", user.getAge());
//        mongoTemplate.updateMulti(query, update, User.class);
//    }
//
//    /**
//     * 根据主键查询
//     * <br>------------------------------<br>
//     * @param id
//     * @return
//     */
//    public User findById(String id) {
//        return mongoTemplate.findById(id, User.class);
//    }
//
//    /**
//     * 查询全部
//     * <br>------------------------------<br>
//     * @return
//     */
//    public List<User> findAll() {
//        return mongoTemplate.findAll(User.class);
//    }
//
//    /**
//     * 按条件查询, 分页
//     * <br>------------------------------<br>
//     * @param criteriaUser
//     * @param skip
//     * @param limit
//     * @return
//     */
//    public List<User> find(User criteriaUser, int skip, int limit) {
//        Query query = getQuery(criteriaUser);
//        query.skip(skip);
//        query.limit(limit);
//        return mongoTemplate.find(query, User.class);
//    }
//
//    /**
//     * 根据条件查询出来后 再去修改
//     * <br>------------------------------<br>
//     * @param criteriaUser  查询条件
//     * @param updateUser    修改的值对象
//     * @return
//     */
//    public User findAndModify(User criteriaUser, User updateUser) {
//        Query query = getQuery(criteriaUser);
//        Update update = Update.update("age", updateUser.getAge()).set("name", updateUser.getName());
//        return mongoTemplate.findAndModify(query, update, User.class);
//    }
//
//    /**
//     * 查询出来后 删除
//     * <br>------------------------------<br>
//     * @param criteriaUser
//     * @return
//     */
//    public User findAndRemove(User criteriaUser) {
//        Query query = getQuery(criteriaUser);
//        return mongoTemplate.findAndRemove(query, User.class);
//    }
//
//    /**
//     * count
//     * <br>------------------------------<br>
//     * @param criteriaUser
//     * @return
//     */
//    public long count(User criteriaUser) {
//        Query query = getQuery(criteriaUser);
//        return mongoTemplate.count(query, User.class);
//    }
//
//    /**
//     *
//     * <br>------------------------------<br>
//     * @param criteriaUser
//     * @return
//     */
//    private Query getQuery(User criteriaUser) {
//        if (criteriaUser == null) {
//            criteriaUser = new User();
//        }
//        Query query = new Query();
//        if (criteriaUser.getId() != null) {
//            Criteria criteria = Criteria.where("id").is(criteriaUser.getId());
//            query.addCriteria(criteria);
//        }
//        if (criteriaUser.getAge() > 0) {
//            Criteria criteria = Criteria.where("age").gt(criteriaUser.getAge());
//            query.addCriteria(criteria);
//        }
//        if (criteriaUser.getName() != null) {
//            Criteria criteria = Criteria.where("name").regex("^" + criteriaUser.getName());
//            query.addCriteria(criteria);
//        }
//        return query;
//    }
}