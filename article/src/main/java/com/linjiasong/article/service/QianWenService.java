package com.linjiasong.article.service;

import com.linjiasong.article.entity.dto.QianWenChatDTO;
import com.linjiasong.article.excepiton.ArticleBaseResponse;

/**
 * @author linjiasong
 * @date 2025/2/13 上午11:30
 */
public interface QianWenService {

    ArticleBaseResponse<?> chat(QianWenChatDTO qianWenChatDTO);

}
