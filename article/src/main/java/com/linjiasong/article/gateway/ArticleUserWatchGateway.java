package com.linjiasong.article.gateway;

import com.linjiasong.article.entity.ArticleUserWatch;

import java.util.List;

/**
 * @author linjiasong
 * @date 2025/1/24 上午10:26
 */
public interface ArticleUserWatchGateway {

    boolean insert(ArticleUserWatch articleUserWatch);

    List<ArticleUserWatch> getUserWatchList(Long userId);

    boolean isExist(Long articleId, Long userId);

    boolean deleteUserWatch(List<Long> ids);

    List<ArticleUserWatch> checkAndGetUserWatchList(List<Long> ids, Long userId);

    Short getTag();
}
