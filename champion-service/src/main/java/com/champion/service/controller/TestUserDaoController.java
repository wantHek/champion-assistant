package com.champion.service.controller;

import com.champion.service.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yongjie.pei on 2017/8/2.
 */
@Controller
public class TestUserDaoController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;

    @RequestMapping(value="/user",method={RequestMethod.POST})
    @ResponseBody
    public String getScreenResout(@RequestBody String userJson){
        String resultMsg = "";
        try {
            userDao.insert(userJson);
            resultMsg = "1";
            logger.info("成功");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(resultMsg != null && !"".equals(resultMsg)){
                resultMsg = "0";
                logger.info("失败");
            }else{
                resultMsg = "1";
            }
        }
        return resultMsg;
    }

}
