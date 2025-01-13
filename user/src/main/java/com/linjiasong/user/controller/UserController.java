package com.linjiasong.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linjiasong
 * @date 2025/1/13 下午4:42
 */

@RestController
@RequestMapping(("/user"))
public class UserController {


    @GetMapping
    public String testController(){
        return "hello world!!!";
    }

}
