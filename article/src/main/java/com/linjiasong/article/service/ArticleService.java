package com.linjiasong.article.service;

import com.linjiasong.article.entity.dto.ArticleCreateDTO;
import com.linjiasong.article.excepiton.ArticleBaseResponse;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:39
 */
public interface ArticleService {

    /**
     * 新建文章
     * @param articleCreateDTO articleCreateDTO
     * @return ArticleBaseResponse
     */
    ArticleBaseResponse createArticle(ArticleCreateDTO articleCreateDTO);


    /**
     * 获取用户的文章
     * @param userId userId
     * @return ArticleBaseResponse
     */
    ArticleBaseResponse getUserArticleBasic(Long userId);
}
