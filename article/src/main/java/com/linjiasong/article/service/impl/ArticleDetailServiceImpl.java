package com.linjiasong.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linjiasong.article.entity.ArticleDetail;
import com.linjiasong.article.mapper.ArticleDetailMapper;
import com.linjiasong.article.service.ArticleDetailService;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:17
 */
@Service
public class ArticleDetailServiceImpl extends ServiceImpl<ArticleDetailMapper, ArticleDetail> implements ArticleDetailService {
}
