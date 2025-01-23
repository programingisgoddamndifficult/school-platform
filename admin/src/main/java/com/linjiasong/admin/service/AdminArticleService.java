package com.linjiasong.admin.service;

import com.linjiasong.admin.entity.dto.ArticleCheckDTO;
import com.linjiasong.admin.excepiton.AdminBaseResponse;

/**
 * @author linjiasong
 * @date 2025/1/23 下午5:31
 */
public interface AdminArticleService {

    /**
     * 获取文章待check列表的第一个
     * @return AdminBaseResponse
     */
    AdminBaseResponse getCheckArticleListFirst();

    AdminBaseResponse checkArticle(ArticleCheckDTO articleCheckDTO);
}
