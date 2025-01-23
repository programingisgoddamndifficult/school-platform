package com.linjiasong.admin.controller;

import com.linjiasong.admin.entity.dto.ArticleCheckDTO;
import com.linjiasong.admin.excepiton.AdminBaseResponse;
import com.linjiasong.admin.service.AdminArticleService;
import jakarta.ws.rs.POST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author linjiasong
 * @date 2025/1/23 下午5:38
 */

@RestController
@RequestMapping("/api/admin/article")
public class AdminArticleController {

    @Autowired
    private AdminArticleService adminArticleService;

    //TODO check之后，怎么从list移出去，怎么保证只能check一次，update后也要check
    @GetMapping
    public AdminBaseResponse getArticleCheckListFirst(){
        return adminArticleService.getCheckArticleListFirst();
    }

    @PostMapping
    public AdminBaseResponse checkArticleListFirst(@RequestBody ArticleCheckDTO articleCheckDTO){
        return adminArticleService.checkArticle(articleCheckDTO);
    }

}
