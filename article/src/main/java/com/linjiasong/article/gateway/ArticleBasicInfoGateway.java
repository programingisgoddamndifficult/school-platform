package com.linjiasong.article.gateway;

import com.linjiasong.article.entity.ArticleBasicInfo;

import java.util.List;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:41
 */
public interface ArticleBasicInfoGateway {

    boolean insert(ArticleBasicInfo articleBasicInfo);

    boolean update(ArticleBasicInfo articleBasicInfo);

    List<ArticleBasicInfo> getByUserId(Long userId);

    boolean deleteById(Long id);

    boolean isThisUserArticle(Long id);

    boolean canDelete(Long id);

    ArticleBasicInfo selectById(Long id);

    boolean deleteByUserId(Long userId);

    boolean canOpen(Long articleId);

    List<ArticleBasicInfo> selectByIdsList(List<Long> ids);
}
