package com.linjiasong.user.service.impl;

import com.linjiasong.user.constant.UserInfoContext;
import com.linjiasong.user.entity.dto.ArticleCreateDTO;
import com.linjiasong.user.entity.dto.ArticleUpdateDTO;
import com.linjiasong.user.excepiton.UserBaseResponse;
import com.linjiasong.user.feign.ArticleServiceClient;
import com.linjiasong.user.service.UserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/01/15 21:16
 */
@Service
public class UserArticleServiceImpl implements UserArticleService {

    @Autowired
    ArticleServiceClient articleServiceClient;

    @Override
    public UserBaseResponse getUserArticleBasic() {
        return articleServiceClient.getArticleBasicByUserId(UserInfoContext.get().getId());
    }

    @Override
    public UserBaseResponse createArticle(ArticleCreateDTO articleCreateDTO) {
        return articleServiceClient.createArticle(articleCreateDTO);
    }

    @Override
    public UserBaseResponse updateArticle(ArticleUpdateDTO articleUpdateDTO) {
        return articleServiceClient.updateArticle(articleUpdateDTO);
    }

    @Override
    public UserBaseResponse deleteArticle(Long id) {
        return articleServiceClient.deleteArticle(id);
    }
}
