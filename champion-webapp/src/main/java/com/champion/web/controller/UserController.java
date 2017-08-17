package com.champion.web.controller;

import com.champion.service.service.UserService;
import com.champion.web.models.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yongjie.pei on 2017/8/16.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = { RequestMethod.POST,RequestMethod.GET })
    @ResponseBody
    @CrossOrigin(origins = {"http://localhost:4200","null"})
    public String userLogin(@RequestBody String userJson) throws Exception {
        String find = userService.doWork(userJson, "find");
        return find;
    }
}
