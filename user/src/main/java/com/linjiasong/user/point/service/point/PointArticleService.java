package com.linjiasong.user.point.service.point;

import com.linjiasong.user.constant.RedisKeyEnum;
import com.linjiasong.user.point.dto.PointArticleDTO;
import com.linjiasong.user.point.enums.PointTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RScoredSortedSet;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/22 上午10:54
 */
@Service("POINT_ARTICLE")
@Slf4j
public class PointArticleService extends AbstractPointService {

    @Override
    public <T> void point(PointTypeEnum pointTypeEnum, T dto) {
        PointArticleDTO articleDTO = (PointArticleDTO) dto;
        log.info("POINT_ARTICLE point --- articleId : {}", articleDTO.getArticleId());
        pointService.execute(() -> {
            RAtomicLong atomicLong = redissonClient.getAtomicLong(String.format(RedisKeyEnum.POINT_ARTICLE.getKey(), articleDTO.getArticleId()));
            atomicLong.incrementAndGet();

            hotArticle(articleDTO.getArticleId());
        });
    }

    private void hotArticle(Long articleId){
        RScoredSortedSet<Long> scoredSortedSet = redissonClient.getScoredSortedSet(RedisKeyEnum.POINT_ARTICLE_SCORED.getKey());
        if (scoredSortedSet.contains(articleId)) {
            Double score = scoredSortedSet.getScore(articleId);
            score = score + 1;
            scoredSortedSet.remove(articleId);
            scoredSortedSet.add(score, articleId);
        }else{
            scoredSortedSet.add(1, articleId);
        }
    }

}
