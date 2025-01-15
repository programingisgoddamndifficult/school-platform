package com.linjiasong.article.gateway;

import com.linjiasong.article.entity.ArticleBasicInfo;

import java.util.List;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:41
 */
public interface ArticleBasicInfoGateway {

    boolean insert(ArticleBasicInfo articleBasicInfo);

    List<ArticleBasicInfo> getByUserId(Long userId);

}
