package com.champion.service.service;

import java.io.IOException;

/**
 * Created by yongjie.pei on 2017/8/3.
 */
public interface UserService {
    /**
     * 公共入口
     * <br>------------------------------<br>
     * @param userJson
     */
    public String doWork(String userJson,String operationType) throws Exception;
}
