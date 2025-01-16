package com.linjiasong.user.service;

import com.linjiasong.user.entity.dto.ArticleCreateDTO;
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
     * 创建文章
     * @param articleCreateDTO articleCreateDTO
     * @return UserBaseResponse
     */
    UserBaseResponse createArticle(ArticleCreateDTO articleCreateDTO);
}
