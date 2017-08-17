package com.champion.web.sqlmanager;

import com.champion.web.models.MD5IndexModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yongjie.pei on 2017/8/7.
 */
@Component
public class IndexSqlManager {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 新增
     * <br>------------------------------<br>
     * @param md5IndexModel
     */
    public MD5IndexModel insert(MD5IndexModel md5IndexModel) {
        mongoTemplate.insert(md5IndexModel,"md5Index");
        return md5IndexModel;
    }

    /**
     * 根据条件查询
     *
     */
    public List<MD5IndexModel> findForRequery(String md5Index) {
        Query query = new Query();
        Criteria criteria = new Criteria("md5Index");
        criteria.is(md5Index);
        query.addCriteria(criteria);
        // 查询条件，集合对应的实体类，集合名
        List<MD5IndexModel> userList = mongoTemplate.find(query, MD5IndexModel.class,
                "md5Index");
        return userList;
    }

    /**
     * 按条件删除数据
     */
    public void removeMD5Index(String id) {
        // 设置删除条件，如果条件内容为空则删除所有
        Query query = new Query();
        Criteria criteria = new Criteria("_id");
        criteria.is(id);
        query.addCriteria(criteria);
        mongoTemplate.remove(query, "md5Index");
    }

}
