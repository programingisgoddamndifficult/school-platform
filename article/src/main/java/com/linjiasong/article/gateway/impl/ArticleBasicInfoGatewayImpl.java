package com.linjiasong.article.gateway.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linjiasong.article.constant.ArticleContext;
import com.linjiasong.article.entity.ArticleBasicInfo;
import com.linjiasong.article.entity.dto.ArticlePageSelectDTO;
import com.linjiasong.article.gateway.ArticleBasicInfoGateway;
import com.linjiasong.article.mapper.ArticleBasicInfoMapper;
import com.linjiasong.article.mapper.ArticleUserWatchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:42
 */
@Service
public class ArticleBasicInfoGatewayImpl implements ArticleBasicInfoGateway {

    @Autowired
    ArticleBasicInfoMapper articleBasicInfoMapper;

    @Autowired
    ArticleUserWatchMapper articleUserWatchMapper;

    @Override
    public boolean insert(ArticleBasicInfo articleBasicInfo) {
        return articleBasicInfoMapper.insert(articleBasicInfo) > 0;
    }

    @Override
    public boolean update(ArticleBasicInfo articleBasicInfo) {
        return articleBasicInfoMapper.updateById(articleBasicInfo) > 0;
    }

    @Override
    public boolean update(UpdateWrapper<ArticleBasicInfo> updateWrapper) {
        return articleBasicInfoMapper.update(updateWrapper) > 0;
    }

    @Override
    public List<ArticleBasicInfo> getByUserId(Long userId) {
        return articleBasicInfoMapper.selectList(new QueryWrapper<ArticleBasicInfo>().eq("user_id", userId));
    }

    @Override
    public Page<ArticleBasicInfo> getByUserId(int current, int size, Long userId) {
        return articleBasicInfoMapper.selectPage(new Page<>(current, size), new QueryWrapper<ArticleBasicInfo>().eq("user_id", userId));
    }

    @Override
    public boolean deleteById(Long id) {
        return articleBasicInfoMapper.deleteById(id) > 0;
    }

    @Override
    public boolean isThisUserArticle(Long id) {
        ArticleBasicInfo articleBasicInfo = articleBasicInfoMapper.selectOne(new QueryWrapper<ArticleBasicInfo>().eq("id", id));
        if (articleBasicInfo == null) {
            return false;
        }

        if (articleBasicInfo.isOpen()) {
            return true;
        }

        Long userId = ArticleContext.get().getId();
        return userId.equals(articleBasicInfo.getUserId());
    }

    @Override
    public boolean canDelete(Long id) {
        Long userId = ArticleContext.get().getId();
        return articleBasicInfoMapper.selectOne(new QueryWrapper<ArticleBasicInfo>().eq("id", id).eq("user_id", userId)) != null;
    }

    @Override
    public ArticleBasicInfo selectById(Long id) {
        return articleBasicInfoMapper.selectById(id);
    }

    @Override
    public boolean deleteByUserId(Long userId) {
        return articleBasicInfoMapper.delete(new QueryWrapper<ArticleBasicInfo>().eq("user_id", userId)) > 0;
    }

    @Override
    public boolean canOpen(Long articleId) {
        Long userId = ArticleContext.get().getId();
        return articleBasicInfoMapper.selectOne(new QueryWrapper<ArticleBasicInfo>().eq("id", articleId).eq("user_id", userId)) != null;
    }

    @Override
    public List<ArticleBasicInfo> selectByIdsList(List<Long> ids) {
        return articleBasicInfoMapper.selectBatchIds(ids);
    }

    @Override
    public Page<ArticleBasicInfo> unRecommend(ArticlePageSelectDTO articlePageSelectDTO) {
        return articleBasicInfoMapper.selectPage(new Page<>(articlePageSelectDTO.getCurrent(),
                articlePageSelectDTO.getSize()), articlePageSelectDTO.buildQueryWrapper());
    }

    @Override
    public Page<ArticleBasicInfo> recommend(int current, int size, boolean hasRecommendHistory, Short tag, Long bigArticleId) {
        if (!hasRecommendHistory) {
            return articleBasicInfoMapper.selectPage(new Page<>(current, size), buildRecommendQueryWrapper(false, bigArticleId));
        }

        /**
         * size默认都是20个，tag中的内容至少占一半；
         * 每次默认在数据库中查询30条，
         * 即要在30条中取10条该tag的数据，
         * 剩下10条在剩下的20条数据中随机抽取
         * 最后记录返回的20条中 最大的articleId;
         *
         * 如果不够一半 那么也无所谓 本次查询算推荐不足
         * */
        Page<ArticleBasicInfo> page = articleBasicInfoMapper.selectPage(new Page<>(1, 30),
                buildRecommendQueryWrapper(true, bigArticleId));
        List<ArticleBasicInfo> records = page.getRecords();

        if (records.size() <= 20) {
            if (records.isEmpty()) {
                return articleBasicInfoMapper.selectPage(new Page<>(1, size),
                        buildNoRecordsQueryWrapper(bigArticleId));
            }
            return page;
        }

        page.setCurrent(1);
        page.setSize(size);
        page.setTotal(20);

        Map<Short, List<ArticleBasicInfo>> recordsMap = records.stream().collect(Collectors.groupingBy(ArticleBasicInfo::getTag));
        List<ArticleBasicInfo> tagRecords = recordsMap.remove(tag);
        if (tagRecords == null) {
            tagRecords = new ArrayList<>();
        }
        if (tagRecords.size() >= 20) {
            tagRecords = tagRecords.subList(0, 20);
            page.setRecords(tagRecords);
            return page;
        }

        //剩余需补全的records数
        int remainderRecordsNum = 20 - tagRecords.size();

        Collection<List<ArticleBasicInfo>> remainderRecordsGroups = recordsMap.values();

        int i = 0;
        while (remainderRecordsNum > 0) {
            for (List<ArticleBasicInfo> remainderRecords : remainderRecordsGroups) {
                if (remainderRecords.size() < i + 1) {
                    continue;
                }
                tagRecords.add(remainderRecords.get(i));
                if (--remainderRecordsNum <= 0) {
                    break;
                }
            }
            i++;
        }

        if (tagRecords.isEmpty()) {
            return articleBasicInfoMapper.selectPage(new Page<>(1, size),
                    buildNoRecordsQueryWrapper(bigArticleId));
        }

        page.setRecords(tagRecords);
        return page;
    }

    @Override
    public Page<ArticleBasicInfo> orderRecommend(int current, int size, boolean hasRecommendHistory, Long bigArticleId) {
        if (!hasRecommendHistory) {
            return articleBasicInfoMapper.selectPage(new Page<>(current, size), buildRecommendQueryWrapper(false, bigArticleId));
        }

        Page<ArticleBasicInfo> page = articleBasicInfoMapper.selectPage(new Page<>(1, size),
                buildRecommendQueryWrapper(true, bigArticleId));

        if (page.getRecords().isEmpty()) {
            return articleBasicInfoMapper.selectPage(new Page<>(1, size),
                    buildNoRecordsQueryWrapper(bigArticleId));
        }

        return page;
    }

    private QueryWrapper<ArticleBasicInfo> buildRecommendQueryWrapper(boolean hasRecommendHistory, Long bigArticleId) {
        if (!hasRecommendHistory) {
            return new QueryWrapper<ArticleBasicInfo>()
                    .eq("is_check", 1)
                    .eq("is_open", 1)
                    .eq("is_ban", 0)
                    .orderByAsc("id");
        }

        return new QueryWrapper<ArticleBasicInfo>()
                .eq("is_check", 1)
                .eq("is_open", 1)
                .eq("is_ban", 0)
                .gt("id", bigArticleId)
                .orderByAsc("id");
    }

    private QueryWrapper<ArticleBasicInfo> buildNoRecordsQueryWrapper(Long bigArticleId) {
        return new QueryWrapper<ArticleBasicInfo>()
                .eq("is_check", 1)
                .eq("is_open", 1)
                .eq("is_ban", 0)
                .lt("id", bigArticleId)
                .orderByAsc("id");
    }
}
