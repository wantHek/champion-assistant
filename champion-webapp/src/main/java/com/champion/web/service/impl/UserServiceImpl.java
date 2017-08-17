package com.champion.web.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.champion.service.service.UserService;
import com.champion.service.utils.StringUtils;
import com.champion.web.models.MD5IndexModel;
import com.champion.web.models.UserModel;
import com.champion.web.sqlmanager.IndexSqlManager;
import com.champion.web.sqlmanager.UserSqlManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by yongjie.pei on 2017/8/3.
 */
@Service
public class UserServiceImpl implements UserService{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserSqlManager userSqlManger;
    @Autowired
    private IndexSqlManager indexSqlManager;

    @Override
    public String doWork(String userJson,String operationType) throws IOException, NoSuchAlgorithmException {
        String userJsonReturn = "";
        ObjectMapper mapper = new ObjectMapper();
        switch (operationType){
            case "registered":
                userJsonReturn = this.insert(userJson,mapper);
                break;
            case "find":
                userJsonReturn = this.forRequery(userJson,mapper);
                break;
            case "findAll":
                userJsonReturn = this.findAll(mapper);
                break;
            case "update":
                break;
            case "delete":
                break;
            default:
                break;
        }

        return userJsonReturn;
    }

    /**
     * 查询所有账户
     * @param mapper
     * @return
     * @throws JsonProcessingException
     */
    private String findAll(ObjectMapper mapper) throws JsonProcessingException {
        String userJsonReturn = "";
                List<UserModel> findAll = userSqlManger.findAll();
        if(findAll!=null && findAll.size()>0){
            userJsonReturn = mapper.writeValueAsString(findAll);
        }else{
            logger.info("查询失败，没有用户");
            userJsonReturn = "0";
        }
        return userJsonReturn;
    }

    /**
     * 新增
     * <br>------------------------------<br>
     * @param userJson
     */
    private String insert(String userJson,ObjectMapper mapper) throws IOException, NoSuchAlgorithmException {
        String userJsonReturn = "";
        String md5Id = "";
        try {
            md5Id = this.md5Encoding(userJson);
            if(StringUtils.chackStringNull(md5Id)){
                String checkUserName = this.forRequery(userJson,mapper);
                if("0".equals(checkUserName)){
                    UserModel userModel = mapper.readValue(userJson, UserModel.class);
                    UserModel insertUser = userSqlManger.insert(userModel);
                    if(StringUtils.chackStringNull(insertUser.getId())){
                        userJsonReturn = mapper.writeValueAsString(insertUser);
                    }else{
                        logger.info("注册失败");
                        userJsonReturn = "0";//注册失败
                    }
                }else if("1".equals(checkUserName)){
                    throw new Exception("查询账户发生异常");
                }else{
                    logger.info("已注册");
                    userJsonReturn = "1";//已注册
                }
            }else{
                logger.info("重复申请注册");
                userJsonReturn = "2";//重复申请注册
            }
        }catch (NoSuchAlgorithmException se){
            logger.info("md5加密异常",se);
            userJsonReturn = "0";
        }catch (Exception e){
            logger.info("未知异常",e);
            userJsonReturn = "0";
        }finally {
            if(StringUtils.chackStringNull(md5Id)){
                indexSqlManager.removeMD5Index(md5Id);
            }
        }
        return userJsonReturn;
    }

    /**
     * md5加密，占号操作，防止并发重复数据产生
     * @param userJson
     * @return
     * @throws Exception
     */
    private String md5Encoding(String userJson) throws Exception {
        MD5IndexModel insert = new MD5IndexModel();
        String md5Index = "";
        try {
            md5Index = StringUtils.md5Encoding(userJson);
            if(StringUtils.chackStringNull(md5Index)){
                insert.setMd5Index(md5Index);
                insert = indexSqlManager.insert(insert);
            }
        }catch (NoSuchAlgorithmException se){
            throw new NoSuchAlgorithmException("md5加密异常",se);
        }catch (Exception e){
            if(StringUtils.chackStringNull(md5Index)){
                List<MD5IndexModel> forRequery = indexSqlManager.findForRequery(md5Index);
                if(forRequery!=null && forRequery.size()>0){
                    logger.info("重复申请注册",e);
                }else{
                    throw new Exception("未知异常",e);
                }
            }else{
                throw new Exception("未知异常",e);
            }
        }

        return insert.getId();
    }

    /**
     * 新增
     * <br>------------------------------<br>
     * @param userJson
     */
    private String forRequery(String userJson,ObjectMapper mapper){
        String userJsonReturn = "";
        try {
            UserModel userModel = mapper.readValue(userJson, UserModel.class);
            List<UserModel> forRequery = userSqlManger.findForRequery(userModel.getUserName());
            if(forRequery!=null && forRequery.size()>0){
                UserModel userModelJson = forRequery.get(0);
                userJsonReturn = mapper.writeValueAsString(userModelJson);
            }else{
                logger.info("没有该用户");
                userJsonReturn = "0";
            }
        }catch (IOException io){
            logger.info("查询账户发生异常",io);
            userJsonReturn = "1";
        }
        return userJsonReturn;
    }

}
