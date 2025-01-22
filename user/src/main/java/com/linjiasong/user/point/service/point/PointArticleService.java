package com.linjiasong.user.point.service.point;

import com.linjiasong.user.constant.RedisKeyEnum;
import com.linjiasong.user.point.dto.PointArticleDTO;
import com.linjiasong.user.point.service.enums.PointTypeEnum;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.statement.select.KSQLWindow;
import org.redisson.api.RAtomicLong;
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
            String scoredKey = String.format(RedisKeyEnum.POINT_ARTICLE_SCORED.getKey(), articleDTO.getArticleId());
            redissonClient.getScoredSortedSet(scoredKey).addScore(scoredKey, 1.0);
        });
    }

}
