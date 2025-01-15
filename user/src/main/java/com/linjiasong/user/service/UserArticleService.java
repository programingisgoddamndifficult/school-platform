package com.linjiasong.user.service;

import com.linjiasong.user.entity.dto.ArticleUpdateDTO;
import com.linjiasong.user.excepiton.UserBaseResponse;

/**
 * @author linjiasong
 * @date 2025/01/15 21:16
 */
public interface UserArticleService {

    /**
     * 获取文章基础信息
     * @return UserBaseResponse
     */
    UserBaseResponse getUserArticleBasic();

    /**
     * 修改文章
     * @param articleUpdateDTO articleUpdateDTO
     * @return UserBaseResponse
     */
    UserBaseResponse updateArticle(ArticleUpdateDTO articleUpdateDTO);
}
