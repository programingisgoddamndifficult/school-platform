package com.linjiasong.user.controller;

import com.linjiasong.user.entity.dto.ArticleUpdateDTO;
import com.linjiasong.user.entity.dto.UserInfoDTO;
import com.linjiasong.user.entity.dto.UserLoginDTO;
import com.linjiasong.user.excepiton.UserBaseResponse;
import com.linjiasong.user.service.UserArticleService;
import com.linjiasong.user.service.UserInfoService;
import com.linjiasong.user.service.UserLikeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author linjiasong
 * @date 2025/1/13 下午6:10
 */

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    UserLikeService userLikeService;

    @Autowired
    UserArticleService userArticleService;

    @PostMapping("/signup")
    public UserBaseResponse signUp(@RequestBody UserInfoDTO userInfoDTO){
        return userInfoService.signUp(userInfoDTO);
    }

    @PostMapping("/login")
    public UserBaseResponse login(@RequestBody UserLoginDTO userLoginDTO, HttpServletResponse response){
        return userInfoService.login(userLoginDTO, response);
    }

    @GetMapping
    public UserBaseResponse getUserInfo(){
        return userInfoService.getUserInfo();
    }

    @PostMapping("/like/{id}")
    public UserBaseResponse like(@PathVariable("id") Long id){
        return userLikeService.like(id);
    }

    @PostMapping("/black/{id}")
    public UserBaseResponse black(@PathVariable("id") Long id){
        return userLikeService.blackList(id);
    }

    @GetMapping("/article")
    public UserBaseResponse getUserArticle(){
        return userArticleService.getUserArticleBasic();
    }

    //TODO openfeign设置header传递token
    @PostMapping("/article/update")
    public UserBaseResponse updateArticle(ArticleUpdateDTO articleUpdateDTO){
        return userArticleService.updateArticle(articleUpdateDTO);
    }

}
